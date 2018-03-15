package nl.uva.se.sc.niro

import javafx.application.Application
import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.Scene
import javafx.scene.control._
import javafx.scene.layout.{ HBox, VBox }
import javafx.scene.text.Font
import javafx.stage.{ Screen, Stage }
import nl.uva.se.sc.niro.errors.Errors._
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, IntegerAnswer }
import nl.uva.se.sc.niro.model.ql.{ QLForm, Question, Statement }
import org.apache.logging.log4j.scala.Logging

import scala.collection.mutable
import scala.io.Source
import scala.util.Try

/** Interactive REPL to help during development. This is throwaway code */
object REPL {
  def main(args: Array[String]) {
    Application.launch(classOf[REPL], args: _*)
  }
}

class REPL extends Application with Logging {

  var inputFrameText = ""

  var qlForm: Either[Seq[Error], QLForm] = Right(QLForm("", Seq.empty))
  val dictionary: mutable.Map[String, Answer] = mutable.Map.empty

  override def start(primaryStage: Stage) {
    primaryStage.setTitle("Niro Repl")

    val primaryScreenBounds = Screen.getPrimary.getVisualBounds

    // set stage to right half screen
    primaryStage.setX(primaryScreenBounds.getWidth / 2)
    primaryStage.setY(primaryScreenBounds.getHeight)
    primaryStage.setWidth(primaryScreenBounds.getWidth / 2)
    primaryStage.setHeight(primaryScreenBounds.getHeight)

    val inputLabel = new Label("QL Form specification")
    inputLabel.setFont(new Font("Arial", 20))
    val inputTextArea: TextArea = new TextArea
    inputFrameText = getDefaultQLForm
    inputTextArea.setText(inputFrameText)
    val inputFrame = new VBox(inputLabel, inputTextArea)

    parseQL(inputFrameText)

    val qlFormText = new Label(prettyPrintQLForm(qlForm))

    val astLabel = new Label("AST")
    astLabel.setFont(new Font("Arial", 20))
    val astFrame: VBox = new VBox(astLabel, qlFormText)

    val outputLabel = new Label("Questions")
    outputLabel.setFont(new Font("Arial", 20))
    val outputFrame: VBox = new VBox(outputLabel)
    renderQuestions(astFrame, outputFrame, qlForm)

    val hbox = new HBox(inputFrame, astFrame, outputFrame)

    inputTextArea
      .textProperty()
      .addListener(new ChangeListener[String] {
        def changed(p1: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
          logger.debug("change event!")
          parseQL(newValue)
          astFrame.getChildren.remove(1)
          astFrame.getChildren.add(new Label(prettyPrintQLForm(qlForm)))

          outputFrame.getChildren.clear()
          outputFrame.getChildren.add(outputLabel)
          renderQuestions(astFrame, outputFrame, qlForm)
        }
      })

    primaryStage.setScene(new Scene(hbox))
    primaryStage.show()
  }

  def getDefaultQLForm: String = {
    Source.fromResource("examples/example.ql").mkString
  }

  def parseQLInput(newInput: String): Unit = {
    logger.debug("Parsing new input")
    parseQL(newInput)
  }

  def parseQL(newQLInput: String): Unit = {
    qlForm = QLFormService.importQLSpecification(newQLInput)
  }

  def prettyPrintQLForm(qlFormOrError: Either[Seq[Error], QLForm]): String = {
    pprint.apply(qlFormOrError).plainText
  }

  def renderQuestions(astFrame: VBox, outputFrame: VBox, qlFormOrError: Either[Seq[Error], QLForm]): Unit = {
    qlFormOrError.map { qlForm =>
      val allQuestionsInForm = Statement.collectAllQuestions(qlForm.statements)
      val questions = allQuestionsInForm.map {
        case Question(id, label, answerType, expression) =>
          val idField = new Label(id)
          val labelField = new Label(label)
          val inputField = new TextField()

          val answer: Option[Answer] = dictionary.get(id)
          answer foreach {
            case IntegerAnswer(Some(value)) => inputField.setText(value.toString)
            case IntegerAnswer(None)        => ()
          }

          inputField
            .textProperty()
            .addListener(new ChangeListener[String] {
              def changed(p1: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
                logger.debug(s"change event on question: $id")
                val intAnswer = IntegerAnswer(Try(newValue.toInt).toOption)

                dictionary(id) = intAnswer
                val updatedDictionary = qlFormOrError.map(Evaluator.evaluate(_, dictionary.toMap)).toOption.get
                pprint.pprintln(updatedDictionary)
                dictionary ++= mutable.Map(updatedDictionary.toSeq: _*)
                pprint.pprintln(dictionary)
                astFrame.getChildren.remove(1)
                astFrame.getChildren.add(new Label(prettyPrintQLForm(qlFormOrError)))

                val outputLabel = new Label("Questions")
                outputLabel.setFont(new Font("Arial", 20))
                outputFrame.getChildren.clear()
                outputFrame.getChildren.add(outputLabel)
                renderQuestions(astFrame, outputFrame, qlFormOrError)
              }
            })
          new HBox(idField, labelField, inputField)
      }
      questions.foreach(outputFrame.getChildren.add)
    }
  }
}

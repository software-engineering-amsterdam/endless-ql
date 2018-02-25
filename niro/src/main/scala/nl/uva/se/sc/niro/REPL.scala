package nl.uva.se.sc.niro

import javafx.application.Application
import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.Scene
import javafx.scene.control.{ TextArea }
import javafx.scene.layout.HBox
import javafx.stage.{ Screen, Stage }
import nl.uva.se.sc.niro.model.QLForm
import nl.uva.se.sc.niro.parser.QLFormParser
import org.antlr.v4.runtime.ANTLRInputStream
import sext._

import scala.io.Source
import scala.util.{ Failure, Success, Try }
import org.apache.logging.log4j.scala.Logging

object REPL {
  def main(args: Array[String])
  {
    Application.launch(classOf[REPL], args: _*)
  }
}

class REPL extends Application with Logging {
  override def start(primaryStage: Stage) {
    primaryStage.setTitle("NIRO Repl")

    val primaryScreenBounds = Screen.getPrimary.getVisualBounds

    // set stage to right half screen
    primaryStage.setX(primaryScreenBounds.getWidth / 2)
    primaryStage.setY(primaryScreenBounds.getHeight)
    primaryStage.setWidth(primaryScreenBounds.getWidth / 2)
    primaryStage.setHeight(primaryScreenBounds.getHeight)

    val textAreaQL: TextArea = new TextArea
    textAreaQL.setText(getDefaultQLForm)
    val textAreaAST: TextArea = new TextArea
    textAreaAST.setText(parseQLInput(getDefaultQLForm))

    textAreaQL.textProperty().addListener(new ChangeListener[String] {
      def changed(p1: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
        logger.debug("change event!")
        val astText = parseQLInput(newValue)
        textAreaAST.setText(astText)
      }
    })

    val hbox = new HBox(textAreaQL, textAreaAST)
    primaryStage.setScene(new Scene(hbox))
    primaryStage.show
  }

  def getDefaultQLForm: String = {
    Source.fromResource("examples/example.ql").mkString
  }

  def parseQLInput(newInput: String): String = {
    logger.debug("Parsing new input")
    getQLTextAreaText(parseQL(newInput))
  }

  def parseQL(newQLInput: String): Try[QLForm] = {
    val inputStream = new ANTLRInputStream(newQLInput)
    Try(QLFormParser.parse(inputStream))
  }

  def getQLTextAreaText(tryQl: Try[QLForm]) = {
    tryQl match {
      case Success(form) =>
        logger.debug("Succesfully parsed")
        prettyPrintQLForm(form)
      case Failure(ex) =>
        logger.error(s"Failed to parse: ${ex}")
        ex.toString
    }
  }

  def prettyPrintQLForm(qlForm: QLForm): String = {
    qlForm.treeString
  }
}

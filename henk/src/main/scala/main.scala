import grammar._

import org.antlr.v4.runtime._

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
// import scalafx.scene.control.Label
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.input._
import scalafx.event.ActionEvent
import scalafx.beans.property._

object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    scene = new Scene(500, 500) {
        val box = new VBox
        val sp = new ScrollPane
        var st = ""
        var newUI : List[HBox] = getQuestions(st).map(callbackBox(_, boxUpdate))

        def existsId(id: String): Boolean = {
          newUI.exists( hb => hb.id.value == id )
        }

        def getId(id: String): Option[HBox] = {
          newUI.find { case hb: HBox if hb.id.value == id => true}
        }

        def render(): Unit = {
          sp.content = new VBox {
            children = newUI
          }
        }

        def rerender(): Unit = {
          val result = getQuestions(st).map(callbackBox(_, boxUpdate))
          .map {
            case hb: HBox if existsId(hb.id.value) => getId(hb.id.value).get
            case other => other
          }
          newUI = result
          render()
        }

        def getQuestions(value: String): List[String] = {
          if(value == "yes") {
            List("Question 1", "Question 2")
          } else {
            List("Question 1")
          }
        }

        def boxUpdate(value: String): Unit = {
          st = value
          rerender()
        }

        render()
        root = sp
    }
  }

  def callbackBox(question: String, cb: (String) => Unit): HBox = {
    val box = new HBox {
      id = question
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
    }
    val inputField = new TextField {}
    inputField.text.onChange {
      cb(inputField.text())
    }
    box.children = List(new Label(question), inputField)
    box
  }

  def getParser(input:String): QLParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QLLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QLParser(tokens)
    return parser
  }

  def getQLSParser(input:String): QLSParser = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new QLSLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new QLSParser(tokens)
    return parser
  }
}

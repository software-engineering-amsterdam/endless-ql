import grammar._

import org.antlr.v4.runtime._

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
// import scalafx.scene.control.Label
import scalafx.scene.control._
import scalafx.scene.layout.{ BorderPane, HBox, VBox, Priority}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.input._
import scalafx.event.ActionEvent
import scalafx.beans.property._

object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    scene = new Scene {
        root = new VBox {
          val q1Answer = StringProperty("")
          val q2Visible = BooleanProperty(false)
          q1Answer.onChange {(_, _, answer) => q2Visible.value = (answer == "yes")}

          val firstQ = createBox("Show second questions?", q1Answer)
          val secondQ = createBox("second question", q2Visible)
          children = List(firstQ, secondQ)
      }
    }
  }
  def createBox(question: String, prop: StringProperty): HBox = {
    new HBox {
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
      children = List(
        new Label(question),
        new TextField {
          text.onChange {
            prop.value = text()
          }
        }
      )
    }
  }

  def createBox(question: String, isVisible: BooleanProperty): HBox = {
    val box = new HBox {
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      visible = isVisible.value
      padding = Insets(20)
      children = List(
        new Label(question),
        new TextField {
          text.onChange {
            println(text())
          }
        }
      )
    }
    isVisible.onChange((_,_, newVal) => box.visible = newVal)
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

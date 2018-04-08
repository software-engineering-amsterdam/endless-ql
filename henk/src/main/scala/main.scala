// import grammar._
import scala.io.Source

// import org.antlr.v4.runtime._
import ql.parsers._
import ql.ui._
import ql.models.ast._
import ql.evaluators._

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
  val location = getClass.getResource("/simple.ql")
  val root = QLParser.getRoot(location)
  val evaluator = new SymbolTableEvaluator(root)
  var programState = collection.mutable.Map[Identifier, ExpressionValue]()
  val factory = new QuestionFactory(programState)

  stage = new JFXApp.PrimaryStage {
    scene = new Scene(500, 500) {
        val box = new VBox
        val sp = new ScrollPane
        var newUI : List[HBox] = List()
        var UIstatements : List[Statement] = List()

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
          val result = getQuestions().map(factory.create(_, rerender))
          .map {
            case hb: HBox if existsId(hb.id.value) => getId(hb.id.value).get
            case other => other
          }
          newUI = result
          render()
        }

        def getQuestions(): List[Question] = {
          evaluator.getReachableQuestions(programState)
        }

        def boxUpdate(value: String): Unit = {
          rerender()
        }

        newUI = getQuestions().map(factory.create(_, rerender))
        render()
        root = sp
    }
  }
}

package ql.app

import scala.io.Source
import java.net.URL

// import org.antlr.v4.runtime._
import ql.parsers._
import ql.ui._
import ql.models.ast._
import ql.evaluators._
import ql.validators._

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.input._
import scalafx.event.ActionEvent
import scalafx.beans.property._

class QLApp(fileLocation: URL) extends JFXApp {

  override def main(args: Array[String]) = {
    super.main(args)
  }

  var components: List[HBox] = List()
  var error: Option[String] = None
  var warnings: Option[List[String]] = None

  val validator = new TypeChecker()
  val root = QLParser.getRoot(fileLocation)
  parse(root)

  def parse(ast: Root): Unit = {
    try {
      validator.run(ast)
      warnings = validator.warnings
    } catch {
      case ex: IdentifierNotDeclared => error = Some(ex.label)
      case ex: ConditionalNotBoolean => error = Some(ex.label)
      case ex: DuplicateQuestionDeclaration => error = Some(ex.label)
      case ex: InvalidTypeInfered => error = Some(ex.label)
    }
  }

  if(error.isEmpty) {
    val evaluator = new SymbolTableEvaluator(root)
    var programState = collection.mutable.Map[Identifier, ExpressionValue]()
    val factory = new QuestionFactory(programState)
    val compFactory = new ComputationFactory(programState, evaluator)

    stage = new JFXApp.PrimaryStage {
      scene = new Scene(500, 500) {
          val box = new VBox
          val sp = new ScrollPane

          def existsId(id: String): Boolean = {
            components.exists( hb => hb.id.value == id )
          }

          def getId(id: String): Option[HBox] = {
            components.find {
              case hb: HBox if hb.id.value == id => true
              case other => false
            }
          }

          def render(): Unit = {
              val warningBox = warnings.map((warningList) => {
                warningList.map(warning => new Label(warning))
              }).getOrElse(List(new Label("No warnings")))

              val rightBox = new VBox {
                children = warningBox
              }

              val leftBox = new VBox {
                children = components
              }
              sp.content = new BorderPane {
                padding = Insets(20)
                left = leftBox
                right = rightBox
              }
          }

          def rerender(): Unit = {
            val result = evaluator.getReachableStatements(programState)
              .map(stat => {
                stat match {
                  case comp: Computation => compFactory.create(comp, rerender)
                  case quest: Question => {
                    val q = factory.create(quest, rerender)
                    if(existsId(q.id.value)) {
                      getId(q.id.value).get
                    } else {
                      q
                    }
                  }
                }
              })

            components = result
            render()
          }

          components = evaluator.getReachableStatements(programState)
            .map(stat => {
              stat match {
                case comp: Computation => compFactory.create(comp, rerender)
                case quest: Question => factory.create(quest, rerender)
              }
            })

          render()
          root = sp
      }
    }
  } else {
    stage = new JFXApp.PrimaryStage {
      scene = new Scene(500, 500) {
        val sp = new ScrollPane
        sp.content = new Label(error.get)
        root = sp
      }
    }
  }
}

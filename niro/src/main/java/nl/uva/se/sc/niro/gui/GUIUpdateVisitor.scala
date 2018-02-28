package nl.uva.se.sc.niro.gui

import javafx.scene.Node
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.scene.layout.GridPane
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model._

import scala.collection.{ JavaConverters, Seq }

object GUIUpdateVisitor {
  def visit(grid: GridPane, statements: Seq[Statement], symbolTable: Map[String, Expression]): Unit = {
    val controls: Seq[Node] = JavaConverters.asScalaBuffer(grid.getChildren).filterNot(node => node.isInstanceOf[Label])

    controls.zip(statements) foreach {
      case (control, statement) => statement match {
        case Question(_, _, answerType, _, answer) => {
          answerType match {
            case BooleanType =>
              answer.flatMap(_.possibleValue) match {
                case b: Option[Boolean] => b.foreach(control.asInstanceOf[CheckBox].setSelected(_))
              }
            case IntegerType =>
              control.asInstanceOf[TextField].setText(answer.flatMap(_.possibleValue.map(_.toString)).getOrElse(""))
            case DecimalType =>
              control.asInstanceOf[TextField].setText(answer.flatMap(_.possibleValue.map(_.toString)).getOrElse(""))
            case MoneyType =>
              control.asInstanceOf[TextField].setText(answer.flatMap(_.possibleValue.map(_.toString)).getOrElse(""))
            case StringType =>
              control.asInstanceOf[TextField].setText(answer.flatMap(_.possibleValue.map(_.toString)).getOrElse(""))
            case DateType =>
              control.asInstanceOf[DatePicker].getConverter.fromString(answer.flatMap(_.possibleValue.map(_.toString)).getOrElse(""))
          }
        }
        case Conditional(predicate, thenStatements) => {
          Evaluator.evaluateExpression(predicate, symbolTable).possibleValue match {
            case b: Option[Boolean] => b.foreach(control.asInstanceOf[GridPane].setVisible(_))
          }
          visit(control.asInstanceOf[GridPane], thenStatements, symbolTable)
        }
      }
    }
  }
}
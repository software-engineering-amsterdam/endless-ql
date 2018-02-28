package nl.uva.se.sc.niro.gui

import javafx.scene.Node
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.scene.layout.GridPane
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model._

import scala.collection.{ JavaConverters, Seq }

object GUIUpdateVisitor {
  def visit(grid: GridPane, statements: Seq[Statement], symbolTable: Map[String, Expression]): Unit = {
    val controls: Seq[Node] = JavaConverters.asScalaBuffer(grid.getChildren).filterNot(node => node.isInstanceOf[Label])

    controls.zip(statements) foreach {
      case (control, statement) =>
        statement match {
          case Question(_, _, _, _, answer) => {
            answer match {
              case Some(BooleanAnswer(b)) =>
                b.foreach(control.asInstanceOf[CheckBox].setSelected(_))
              case Some(IntAnswer(i)) =>
                control.asInstanceOf[TextField].setText(i.map(_.toString).getOrElse(""))
              case Some(DecAnswer(d)) =>
                control.asInstanceOf[TextField].setText(d.map(_.toString).getOrElse(""))
              case Some(MoneyAnswer(m)) =>
                control.asInstanceOf[TextField].setText(m.map(_.toString).getOrElse(""))
              case Some(StringAnswer(s)) =>
                control.asInstanceOf[TextField].setText(s.map(_.toString).getOrElse(""))
              case Some(DateAnswer(d)) =>
                control
                  .asInstanceOf[DatePicker]
                  .getConverter
                  .fromString(d.map(_.toString).getOrElse(""))
              case _ => ()
            }
          }
          case Conditional(predicate, thenStatements) => {
            predicate match {
              case BooleanAnswer(b) => b.foreach(control.asInstanceOf[GridPane].setVisible(_))
              case _                      => ()
            }
            visit(control.asInstanceOf[GridPane], thenStatements, symbolTable)
          }
          case ErrorStatement() => ()
        }
    }
  }
}

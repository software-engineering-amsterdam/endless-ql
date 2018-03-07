package nl.uva.se.sc.niro.gui

import javafx.scene.Node
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.scene.layout.GridPane
import nl.uva.se.sc.niro.gui.util.HierarchyUtil
import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model.Expressions.{ Answer, Expression }
import nl.uva.se.sc.niro.model._

import scala.collection.{ JavaConverters, Seq }

object GUIUpdateVisitor {

  def visit(grid: GridPane, statements: Seq[Statement], symbolTable: Map[String, Expression]): Unit = {
    val controls = getNoneLabelControls(grid)
    controls.zip(statements) foreach {
      case (control, statement) =>
        statement match {
          case Question(_, _, _, _, answer) => visitQuestion(control, answer)
          case Conditional(_, thenStatements, answer) => {
            visitConditional(control, answer)
            visit(HierarchyUtil.downcast(control), thenStatements, symbolTable)
          }
          case ErrorStatement() => ()
        }
    }
  }

  def visitQuestion(control: Node, answer: Option[Answer]) = {

    answer match {
      case Some(BooleanAnswer(b)) => {
        val checkBox: CheckBox = HierarchyUtil.downcast(control)
        b.foreach(checkBox.setSelected(_))
      }
      case Some(IntAnswer(i)) => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setText(i.map(_.toString).getOrElse(""))
      }
      case Some(DecAnswer(d)) => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setText(d.map(_.toString).getOrElse(""))
      }
      case Some(MoneyAnswer(m)) => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setText(m.map(_.toString).getOrElse(""))
      }
      case Some(StringAnswer(s)) => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setText(s.map(_.toString).getOrElse(""))
      }
      case Some(DateAnswer(d)) => {
        val datePicker: DatePicker = HierarchyUtil.downcast(control)
        datePicker.setValue(datePicker.getConverter.fromString(d.map(_.toString).getOrElse("")))
      }
      case _ => ()
    }

  }

  def visitConditional(control: Node, predicate: Option[Answer]): Unit = {
    predicate match {
      case Some(BooleanAnswer(b)) => {
        val bool = b.getOrElse(false)
        control.asInstanceOf[GridPane].setVisible(bool)
      }
      case _ => ()
    }
  }

  private def getNoneLabelControls(grid: GridPane) = {
    JavaConverters.asScalaBuffer(grid.getChildren).filterNot(node => node.isInstanceOf[Label])
  }

}

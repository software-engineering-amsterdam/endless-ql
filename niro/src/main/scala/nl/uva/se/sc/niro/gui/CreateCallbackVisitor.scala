package nl.uva.se.sc.niro.gui

import javafx.scene.Node
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.scene.layout.GridPane
import nl.uva.se.sc.niro.gui.util.HierarchyUtil
import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model._

import scala.collection.JavaConverters

object CreateCallbackVisitor {
  def visit(modelUpdater: ModelUpdater, grid: GridPane, statements: Seq[Statement]): Unit = {
    val controls = JavaConverters.asScalaBuffer(grid.getChildren).filterNot(node => node.isInstanceOf[Label])

    controls.zip(statements) foreach {
      case (control, statement) =>
        statement match {
          case Question(questionId, _, answerType, _, _) => visitQuestion(modelUpdater, control, questionId, answerType)
          case Conditional(_, thenStatements, _)         => visit(modelUpdater, HierarchyUtil.downcast(control), thenStatements)
          case ErrorStatement()                          => ()
        }
    }
  }

  def visitQuestion(modelUpdater: ModelUpdater, control: Node, questionId: String, answerType: AnswerType): Unit = {
    answerType match {
      case BooleanType => {
        val checkbox: CheckBox = HierarchyUtil.downcast(control)
        checkbox.setOnAction(_ => {
          modelUpdater.updateModel(questionId, BooleanAnswer(checkbox.isSelected))
        })
      }
      case IntegerType => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setOnAction(_ => modelUpdater.updateModel(questionId, IntAnswer(textField.getText)))
      }
      case DecimalType => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setOnAction(_ => modelUpdater.updateModel(questionId, DecAnswer(textField.getText)))
      }
      case MoneyType => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setOnAction(_ => modelUpdater.updateModel(questionId, MoneyAnswer(textField.getText)))
      }
      case StringType => {
        val textField: TextField = HierarchyUtil.downcast(control)
        textField.setOnAction(_ => modelUpdater.updateModel(questionId, StringAnswer(textField.getText)))
      }
      case DateType => {
        val datePicker: DatePicker = HierarchyUtil.downcast(control)
        datePicker.setOnAction(_ => modelUpdater.updateModel(questionId, DateAnswer("")))
      }
      case _ => ()
    }
  }
}

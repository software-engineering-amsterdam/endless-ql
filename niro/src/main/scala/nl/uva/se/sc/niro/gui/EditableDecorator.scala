package nl.uva.se.sc.niro.gui

import javafx.scene.control.{ CheckBox, DatePicker, TextField }
import nl.uva.se.sc.niro.model.expressions.{ BinaryOperation, Reference, UnaryOperation }
import nl.uva.se.sc.niro.model.Question

object EditableDecorator {

  def makeEditable(checkBox: CheckBox, question: Question, value: Option[Boolean]): CheckBox = {
    question.expression match {
      case Reference(_)             => checkBox.setDisable(true)
      case UnaryOperation(_, _)     => checkBox.setDisable(true)
      case BinaryOperation(_, _, _) => checkBox.setDisable(true)
      case _                        => checkBox.setDisable(value.isDefined)
    }
    checkBox
  }

  // TODO Correct Option type once the AST has settled
  def makeEditable(textField: TextField, question: Question, value: Option[Any]): TextField = {
    question.expression match {
      case Reference(_)             => textField.setDisable(true)
      case UnaryOperation(_, _)     => textField.setDisable(true)
      case BinaryOperation(_, _, _) => textField.setDisable(true)
      case _                        => textField.setDisable(value.isDefined)
    }
    textField.setEditable(!textField.isDisabled)
    textField
  }

  // TODO Correct Option type once the AST has settled
  def makeEditable(datePicker: DatePicker, question: Question, value: Option[Any]): DatePicker = {
    question.expression match {
      case Reference(_)             => datePicker.setDisable(true)
      case UnaryOperation(_, _)     => datePicker.setDisable(true)
      case BinaryOperation(_, _, _) => datePicker.setDisable(true)
      case _                        => datePicker.setDisable(value.isDefined)
    }
    datePicker.setEditable(!datePicker.isDisabled)
    datePicker
  }
}

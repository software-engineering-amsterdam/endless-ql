package ql.ui

import ql.models.ast._

import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.scene.input._
import scalafx.event.ActionEvent
import scalafx.scene.paint.Color

class QuestionFactory(symbolTable: collection.mutable.Map[Identifier, ExpressionValue]) {

  def create(question: Question, cb: () => Unit): HBox = {
    val varDecl = question.varDecl

    val styledQuestion: List[scalafx.scene.Node] = varDecl.typeDecl match {
      case IntegerType => createIntegerQuestion(question, cb)
      case StringType => createStringQuestion(question, cb)
      case BooleanType => createBooleanQuestion(question, cb)
      case MoneyType => createBooleanQuestion(question, cb)
    }

    new HBox {
      id = s"${question.label}_${varDecl.id.toString}"
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
      children = styledQuestion
    }
  }

  def createBooleanQuestion(question: Question, cb: () => Unit): List[scalafx.scene.Node] = {
    val selection = new ChoiceBox[String] {
      maxWidth = 80
      maxHeight = 50
      items = ObservableBuffer("Yes", "No")
      selectionModel().select(1)
    }

    selection.onAction = (ae) => {
      selection.value.value match {
        case "Yes" => symbolTable.update(question.varDecl.id, BooleanValue(true))
        case "No" => symbolTable.update(question.varDecl.id, BooleanValue(false))
      }
      cb()
    }
    List(new Label(question.label), selection)
  }

  def createIntegerQuestion(question: Question, cb: () => Unit): List[scalafx.scene.Node] = {
    val errorMessage = new Label {
      text = ""
      visible = false
      textFill = Color.Red
    }

    def hideError(): Unit = {
      if(errorMessage.visible.value == true) {
        errorMessage.visible = false
      }
    }

    val inputField = new TextField
    var varDecl = question.varDecl

    inputField.text.onChange {
      val fieldContent = inputField.getText()
      if(fieldContent.isEmpty) {
        symbolTable.remove(varDecl.id)
      } else {
        try {
          val amount = Integer.parseInt(fieldContent)
          hideError()
          symbolTable.update(varDecl.id, IntegerValue(amount))
        } catch {
          case ex: NumberFormatException => {
            errorMessage.text = "Field requires an Integer value"
            errorMessage.visible = true
            symbolTable.remove(varDecl.id)
          }
        }
      }
      cb()
    }
    List(new Label(question.label), inputField, errorMessage)
  }

  def createStringQuestion(question: Question, cb: () => Unit): List[scalafx.scene.Node] = {
    val inputField = new TextField
    inputField.text.onChange {
      var varDecl = question.varDecl
      symbolTable.update(varDecl.id, StringValue(inputField.getText()))
      cb()
    }
    List(new Label(question.label), inputField)
  }
}

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

class QuestionFactory(symbolTable: collection.mutable.Map[Identifier, ExpressionValue]) {

  def create(question: Question, cb: () => Unit): HBox = {
    val varDecl = question.varDecl
    varDecl.typeDecl match {
      case IntegerType => createBooleanQuestion(question, cb)
      case StringType => createBooleanQuestion(question, cb)
      case BooleanType => createBooleanQuestion(question, cb)
      case MoneyType => createBooleanQuestion(question, cb)
    }
  }

  def createBooleanQuestion(question: Question, cb: () => Unit): HBox = {
    val box = new HBox {
      id = s"${question.label}_${question.varDecl.id.toString}"
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
    }
    val selection = new ChoiceBox[String] {
      maxWidth = 80
      maxHeight = 50
      items = ObservableBuffer("Yes", "No")
      selectionModel().selectFirst()
    }

    selection.onAction = (ae) => {
      selection.value.value match {
        case "Yes" => symbolTable.update(question.varDecl.id, BooleanValue(true))
        case "No" => symbolTable.update(question.varDecl.id, BooleanValue(false))
      }
      cb()
    }
    box.children = List(new Label(question.label), selection)
    box
  }

  def createIntegerQuestion(question: Question, cb: () => Unit): HBox = {
    val box = new HBox {
      id = s"${question.label}_${question.varDecl.id.toString}"
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
    }
    val inputField = new TextField
    inputField.text.onChange {
      var varDecl = question.varDecl
      symbolTable.update(varDecl.id, IntegerValue(1))
      cb()
    }
    box.children = List(new Label(question.label), inputField)
    box
  }

  def createStringQuestion(question: Question, cb: () => Unit): HBox = {
    val box = new HBox {
      id = s"${question.label}_${question.varDecl.id.toString}"
      vgrow = Priority.Always
      hgrow = Priority.Always
      spacing = 10
      padding = Insets(20)
    }
    val inputField = new TextField
    inputField.text.onChange {
      var varDecl = question.varDecl
      symbolTable.update(varDecl.id, StringValue("some"))
      cb()
    }
    box.children = List(new Label(question.label), inputField)
    box
  }
}

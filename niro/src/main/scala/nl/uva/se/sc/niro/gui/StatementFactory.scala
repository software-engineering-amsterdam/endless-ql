package nl.uva.se.sc.niro.gui

import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.scene.layout.{HBox, VBox}
import javafx.scene.Parent
import javafx.scene.control.{CheckBox, Label, TextField}

import nl.uva.se.sc.niro.model.Ast.AnswerType._
import nl.uva.se.sc.niro.model.Ast._

import scala.collection.JavaConverters

object StatementFactory {

  def createStatements(statements: Seq[Statement]) : java.util.List[Parent] = {
    JavaConverters.seqAsJavaList(statements.map(this.convert))
  }

  def convert(question: Question): Parent = {
    new HBox(new Label(question.label), convert(question.answerType))
  }

  def convert(conditional: Conditional): Parent = {
    val thenPane = new VBox()
    thenPane.setSpacing(10)
    // When invisible we don't occupy any space
    thenPane.managedProperty().bind(thenPane.visibleProperty())

    thenPane.getChildren.addAll(this.createStatements(conditional.ifStatements))

    val elsePane = new VBox()
    elsePane.setSpacing(10)
    // When invisible we don't occupy any space
    elsePane.managedProperty().bind(elsePane.visibleProperty())
    // Exclusive visibility with thenPane
    elsePane.visibleProperty().bind(thenPane.visibleProperty().not())

    elsePane.getChildren.addAll(this.createStatements(conditional.elseStatements))

    new VBox(thenPane, elsePane)
  }

  def convert(statement: Statement): Parent = {
    statement match {
      case q: Question => convert(q)
      case c: Conditional => convert(c)
    }
  }

  def convert(answerType: AnswerType): Parent = {
    answerType match {
      case BooleanAnswerType => new CheckBox()
      case StringAnswerType => new TextField()
      case IntAnswerType => createIntegerField()
      case DecAnswerType => createDecimalField()
      case MoneyAnswerType => createDecimalField()
      case other => new Label(s"Unimplemented type: $other")
    }
  }

  private def createIntegerField(): Parent = {
    createRegExField("\\d+")
  }

  private def createDecimalField(): Parent = {
    createRegExField("\\d*(,\\d{0,2})?")
  }

  private def createRegExField(validPattern: String) = {
    val regexField = new TextField()
    regexField.textProperty().addListener(new ChangeListener[String] {
      override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
        if (!newValue.matches(validPattern)) {
          regexField.setText(oldValue)
        }
      }
    })
    regexField
  }
}

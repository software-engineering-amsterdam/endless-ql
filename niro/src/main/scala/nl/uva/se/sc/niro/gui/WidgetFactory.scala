package nl.uva.se.sc.niro.gui
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.Parent
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.util.StringConverter
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model.Question

object WidgetFactory {
  private val INTEGER_MASK = "\\d*"
  private val DECIMAL_MASK = "\\d*(\\.\\d*)?"
  private val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
  private val DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy")

  def makeWidgets(question: Question, symbolTable: Map[String, Expression]): Seq[Parent] = {
    Seq(new Label(question.label),
      Evaluator.evaluateExpression(question.expression, symbolTable) match {
        case b: BooleanAnswer => makeBooleanField(question, b.possibleValue)
        case s: StringAnswer => makeTextField(question, s.possibleValue)
        case i: IntAnswer => makeIntegerField(question, i.possibleValue)
        case d: DecAnswer => makeDecimalField(question, d.possibleValue)
        case m: MoneyAnswer => makeMoneyField(question, m.possibleValue)
        case d: DateAnswer => makeDateField(question, d.possibleValue)
      })
  }

  def makeBooleanField(question: Question, bool: Option[Boolean]): Parent = {
    val checkbox = new CheckBox()
    bool.foreach(checkbox.setSelected(_))
    EditableDecorator.makeEditable(checkbox, question, bool)
  }
  def makeTextField(question: Question, text: Option[String]): Parent = {
    EditableDecorator.makeEditable(new TextField(text.getOrElse("")), question, text)
  }

  def makeIntegerField(question: Question, value: Option[Int]): Parent = {
    EditableDecorator.makeEditable(makeRegExField(INTEGER_MASK, value.map(_.toString).getOrElse("")), question, value)
  }

  def makeDecimalField(question: Question, value: Option[BigDecimal]): Parent = {
    EditableDecorator.makeEditable(makeRegExField(DECIMAL_MASK, value.map(_.toString).getOrElse("")), question, value)
  }

  def makeMoneyField(question: Question, value: Option[String]): Parent = {
    // TODO Add decimal format with fixed decimals
    EditableDecorator.makeEditable(makeRegExField(MONEY_MASK, value.map(_.toString).getOrElse("")), question, value)
  }

  def makeDateField(question: Question, value: Option[String]): Parent = {
    val dateField = new DatePicker()
    dateField.setConverter(new StringConverter[LocalDate] {

      override def toString(date: LocalDate): String = {
        if (date != null) DATE_FORMAT.format(date) else null
      }

      override def fromString(string: String): LocalDate = {
        if (string != null && !string.isEmpty) LocalDate.parse(string, DATE_FORMAT) else null
      }
    })
    EditableDecorator.makeEditable(dateField, question, value)
  }

  protected def makeRegExField(validPattern: String, value: String): TextField = {
    val regexField = new TextField(value)
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

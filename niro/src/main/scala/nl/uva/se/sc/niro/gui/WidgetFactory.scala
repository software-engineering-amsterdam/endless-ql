package nl.uva.se.sc.niro.gui
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.function.UnaryOperator

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.event.ActionEvent
import javafx.scene.Parent
import javafx.scene.control._
import javafx.util.StringConverter
import javafx.util.converter.{ BigDecimalStringConverter, IntegerStringConverter }
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
    Seq(
      new Label(question.label),
      Evaluator.evaluateExpression(question.expression, symbolTable) match {
        case b: BooleanAnswer => makeBooleanField(question, b.possibleValue)
        case s: StringAnswer  => makeTextField(question, s.possibleValue)
        case i: IntAnswer     => makeIntegerField(question, i.possibleValue)
        case d: DecAnswer     => makeDecimalField(question, d.possibleValue)
        case m: MoneyAnswer   => makeMoneyField(question, m.possibleValue)
        case d: DateAnswer    => makeDateField(question, d.possibleValue)
      }
    )
  }

  def makeBooleanField(question: Question, bool: Option[Boolean]): Parent = {
    val checkbox = new CheckBox()
    EditableDecorator.makeEditable(checkbox, question, bool)
  }
  def makeTextField(question: Question, text: Option[String]): Parent = {
    val textField = new TextField()
    textField
      .textProperty()
      .addListener(new ChangeListener[String] {
        override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
          textField.fireEvent(new ActionEvent(textField, textField))
        }
      })
    EditableDecorator.makeEditable(textField, question, text)
  }

  def makeIntegerField(question: Question, value: Option[Int]): Parent = {
    val integerField = new TextField()
    integerField.setTextFormatter(
      new TextFormatter[java.lang.Integer](new IntegerStringConverter, null, createRegExFilter(INTEGER_MASK)))
    integerField
      .textProperty()
      .addListener(new ChangeListener[String] {
        override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
          if (oldValue != newValue) integerField.fireEvent(new ActionEvent(integerField, integerField))
        }
      })
    EditableDecorator.makeEditable(integerField, question, value)
  }

  def makeDecimalField(question: Question, value: Option[BigDecimal]): Parent = {
    val decimalField = new TextField()
    decimalField.setTextFormatter(
      new TextFormatter[java.math.BigDecimal](new BigDecimalStringConverter, null, createRegExFilter(DECIMAL_MASK)))
    decimalField
      .textProperty()
      .addListener(new ChangeListener[String] {
        override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
          if (oldValue != newValue) decimalField.fireEvent(new ActionEvent(decimalField, decimalField))
        }
      })
    EditableDecorator.makeEditable(decimalField, question, value)
  }

  def makeMoneyField(question: Question, value: Option[String]): Parent = {
    val moneyField = new TextField()
    moneyField.setTextFormatter(new TextFormatter[String](createRegExFilter(MONEY_MASK)))
    EditableDecorator.makeEditable(moneyField, question, value)
  }

  // TODO Change into using LocalDateFormatter or something like that!
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

  private def createRegExFilter(validPattern: String): UnaryOperator[TextFormatter.Change] = {
    val filter = new UnaryOperator[TextFormatter.Change]() {
      override def apply(change: TextFormatter.Change): TextFormatter.Change = {
        if (change.getControlNewText.matches(validPattern)) change else null
      }
    }
    filter
  }

}

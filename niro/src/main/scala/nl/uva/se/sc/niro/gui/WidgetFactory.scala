package nl.uva.se.sc.niro.gui
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.event.ActionEvent
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
    val integerField = makeRegExField(INTEGER_MASK)
    EditableDecorator.makeEditable(integerField, question, value)
  }

  def makeDecimalField(question: Question, value: Option[BigDecimal]): Parent = {
    val decimalField = makeRegExField(DECIMAL_MASK)
    EditableDecorator.makeEditable(decimalField, question, value)
  }

  def makeMoneyField(question: Question, value: Option[String]): Parent = {
    val moneyField = makeRegExField(MONEY_MASK)
    EditableDecorator.makeEditable(moneyField, question, value)
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

  /**
    * Creates a specialized TextField that uses a regular expression when checking if a text is valid. If a text is
    * not valid it is rejected. If a valid text is entered and that is different from the previous value an
    * ActionEvent is fired.
    *
    * @param validPattern defines valid text for the TextField
    * @return a TextField that will respect the pattern when entering text.
    */
  protected def makeRegExField(validPattern: String): TextField = {
    // TODO Investigate textFormatterProperty, it could replace this whole construct!
    val regexField = new TextField()
    regexField
      .textProperty()
      .addListener(new ChangeListener[String] {
        private var previousValue = ""
        override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit = {
          if (!newValue.matches(validPattern)) {
            regexField.setText(oldValue)
          } else {
            if (previousValue != newValue) {
              previousValue = newValue
              regexField.fireEvent(new ActionEvent(regexField, regexField))
            }
          }
        }
      })
    regexField
  }

}

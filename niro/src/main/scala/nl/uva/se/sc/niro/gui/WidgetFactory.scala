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
  private val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")

  def makeWidgets(question: Question, symbolTable: Map[String, Expression]): Seq[Parent] = {
    Seq(new Label(question.label),
      Evaluator.evaluateExpression(question.answer, symbolTable) match {
        case b: BooleanAnswer => makeBooleanField(b.possibleValue)
        case s: StringAnswer => makeTextField(s.possibleValue)
        case i: IntAnswer => makeIntegerField(i.possibleValue)
        case d: DecAnswer => makeDecimalField(d.possibleValue)
        case m: MoneyAnswer => makeMoneyField(m.possibleValue)
        case d: DateAnswer => makeDateField(d.possibleValue)
        case other => new Label(s"Unimplemented type: $other")
      })
  }

  def makeBooleanField(bool: Option[Boolean]): Parent = {
    val checkbox = new CheckBox()
    bool.foreach(checkbox.setSelected(_))
    checkbox
  }

  def makeTextField(text: Option[String]): Parent = {
    new TextField(text.getOrElse(""))
  }

  def makeIntegerField(value: Option[Int]): Parent = {
    makeeRegExField("\\d*", value.map(_.toString).getOrElse(""))
  }

  def makeDecimalField(value: Option[BigDecimal]): Parent = {
    makeeRegExField("\\d*(,\\d{0,2})?", value.map(_.toString).getOrElse(""))
  }

  def makeMoneyField(value: Option[String]): Parent = {
    makeeRegExField("\\d*(,\\d{0,2})?", value.map(_.toString).getOrElse(""))
  }

  def makeDateField(value: Option[String]): Parent = {
    val dateField = new DatePicker()
    dateField.setConverter(new StringConverter[LocalDate] {

      override def toString(date: LocalDate): String = {
        if (date != null) dateFormat.format(date) else null
      }

      override def fromString(string: String): LocalDate = {
        if (string != null && !string.isEmpty) LocalDate.parse(string, dateFormat) else null
      }
    })
    dateField
  }

  protected def makeeRegExField(validPattern: String, value: String): Parent = {
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

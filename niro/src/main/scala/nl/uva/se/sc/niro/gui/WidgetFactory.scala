package nl.uva.se.sc.niro.gui
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.Parent
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.util.StringConverter
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.model.Expressions.{ Answer, Expression }
import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model.Question

object WidgetFactory {
  private val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")

  def makeWidget(question: Question, symbolTable: Map[String, Expression]): Seq[Parent] = {
    Seq(new Label(question.label),
      Evaluator.evaluateExpression(question.answer, symbolTable) match {
      case b: BooleanAnswer => createBooleanField(b.possibleValue)
      case s: StringAnswer => createTextField(s.possibleValue)
      case i: IntAnswer => createIntegerField(i.possibleValue)
      case d: DecAnswer => createDecimalField(d.possibleValue)
      case m: MoneyAnswer => createMoneyField(m.possibleValue)
      case d: DateAnswer => createDateField(d.possibleValue)
      case other => new Label(s"Unimplemented type: $other")
    })
  }

  def createBooleanField(bool: Option[Boolean]): Parent = {
    val checkbox = new CheckBox()
    bool.foreach(checkbox.setSelected(_))
    checkbox
  }

  def createTextField(text: Option[String]): Parent = {
    new TextField(text.getOrElse(""))
  }

  def createIntegerField(value: Option[Int]): Parent = {
    createRegExField("\\d*", value.map(_.toString).getOrElse(""))
  }

  def createDecimalField(value: Option[BigDecimal]): Parent = {
    createRegExField("\\d*(,\\d{0,2})?", value.map(_.toString).getOrElse(""))
  }

  def createMoneyField(value: Option[String]): Parent = {
    createRegExField("\\d*(,\\d{0,2})?", value.map(_.toString).getOrElse(""))
  }

  def createDateField(value: Option[String]): Parent = {
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

  protected def createRegExField(validPattern: String, value: String): Parent = {
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

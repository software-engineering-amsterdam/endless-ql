package nl.uva.se.sc.niro.gui
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.Parent
import javafx.scene.control.{ CheckBox, DatePicker, Label, TextField }
import javafx.util.StringConverter
import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model.Expressions.answers._

object WidgetFactory {
  private val dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")

  def makeWidget(answer: Answer): Parent = {
    answer match {
      case _: BooleanAnswer => new CheckBox()
      case _: StringAnswer => new TextField()
      case _: IntAnswer => createIntegerField()
      case _: DecAnswer => createDecimalField()
      case _: MoneyAnswer => createDecimalField()
      case _: DateAnswer => createDateField
      case other => new Label(s"Unimplemented type: $other")
    }
  }

  def createIntegerField(): Parent = {
    createRegExField("\\d+")
  }

  def createDecimalField(): Parent = {
    createRegExField("\\d*(,\\d{0,2})?")
  }

  def createRegExField(validPattern: String): Parent = {
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

  def createDateField: Parent = {
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
}

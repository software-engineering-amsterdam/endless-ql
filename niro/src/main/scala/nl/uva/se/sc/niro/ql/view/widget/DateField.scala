package nl.uva.se.sc.niro.ql.view.widget

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.DatePicker
import javafx.util.converter.LocalDateStringConverter

class DateField() extends DatePicker with Widget[LocalDate] {
  val DATE_FORMAT = "yyyy-MM-dd"
  valueProperty().addListener(new ChangeListener[LocalDate] {
    override def changed(observable: ObservableValue[_ <: LocalDate], oldValue: LocalDate, newValue: LocalDate): Unit =
      valueChanged()
  })
  private val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
  setConverter(new LocalDateStringConverter(dateFormatter, dateFormatter))
  setPromptText("yyyy-mm-dd")
  override def value(value: LocalDate): Unit = setValue(value)
  override def value: LocalDate = getValue
}

package nl.uva.se.sc.niro.gui.control.ql

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.DatePicker
import javafx.util.converter.LocalDateStringConverter

class QLDateField() extends DatePicker with QLWidget[LocalDate] {
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

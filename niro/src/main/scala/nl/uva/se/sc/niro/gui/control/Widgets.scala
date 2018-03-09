package nl.uva.se.sc.niro.gui.control

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import javafx.scene.control.{ CheckBox, Control, DatePicker, TextField }
import javafx.util.converter.LocalDateStringConverter
import nl.uva.se.sc.niro.gui.builder.TextFormatterBuilder
import nl.uva.se.sc.niro.gui.factory.DateWidgetFactory.DATE_FORMAT
import nl.uva.se.sc.niro.gui.factory.DecimalWidgetFactory.DECIMAL_MASK
import nl.uva.se.sc.niro.gui.factory.IntegerWidgetFactory.INTEGER_MASK
import nl.uva.se.sc.niro.gui.factory.MoneyWidgetFactory.MONEY_MASK

trait QLWidget[T] extends Control {
  def setValue(value: T): Unit
  def getValue: T
}

class QLTextField() extends TextField with QLWidget[String] {
  override def setValue(value: String): Unit = setText(value)
  override def getValue: String = getText
}

class QLDateField() extends DatePicker with QLWidget[LocalDate] {
  private val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
  setConverter(new LocalDateStringConverter(dateFormatter, dateFormatter))
}

class QLBooleanField extends CheckBox with QLWidget[Boolean] {
  override def setValue(value: Boolean): Unit = setSelected(value)
  override def getValue: Boolean = isSelected
}

class QLIntegerField() extends TextField with QLWidget[java.lang.Integer] {
  private val integerFormatter =
    TextFormatterBuilder[Integer]().buildInputFilter(INTEGER_MASK).buildIntegerConverter().build()
  setTextFormatter(integerFormatter)
  override def setValue(value: java.lang.Integer): Unit = integerFormatter.setValue(value)
  override def getValue: java.lang.Integer = integerFormatter.getValue
}

class QLDecimalField() extends TextField with QLWidget[java.math.BigDecimal] {
  private val decimalFormatter =
    TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(DECIMAL_MASK).buildDecimalConverter().build()
  setTextFormatter(decimalFormatter)
  override def setValue(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)
  override def getValue: java.math.BigDecimal = decimalFormatter.getValue
}

class QLMoneyField() extends TextField with QLWidget[java.math.BigDecimal] {
  private val decimalFormatter =
    TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(MONEY_MASK).buildDecimalConverter().build()
  setTextFormatter(decimalFormatter)
  override def setValue(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)
  override def getValue: java.math.BigDecimal = decimalFormatter.getValue
}

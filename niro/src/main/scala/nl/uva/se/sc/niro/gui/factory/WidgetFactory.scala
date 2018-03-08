package nl.uva.se.sc.niro.gui.factory

import java.time.format.DateTimeFormatter

import javafx.scene.control._
import javafx.util.converter.LocalDateStringConverter
import nl.uva.se.sc.niro.gui.builder.TextFormatterBuilder
import nl.uva.se.sc.niro.model.gui.GUIQuestion

trait WidgetFactory {
  val INTEGER_MASK = "\\d*"
  val DECIMAL_MASK = "\\d*(\\.\\d*)?"
  val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
  val DATE_FORMAT = "yyyy-MM-dd"
}

object BooleanWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): Control = {
    new CheckBox()
  }
}

object StringWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): Control = {
    new TextField()
  }
}

object IntegerWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): Control = {
    val integerField = new TextField()
    integerField.setTextFormatter(
      TextFormatterBuilder[java.lang.Integer]().buildInputFilter(INTEGER_MASK).buildIntegerConverter().build())
    integerField
  }
}

object DecimalWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): Control = {
    val decimalField = new TextField()
    decimalField.setTextFormatter(
      TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(DECIMAL_MASK).buildDecimalConverter().build())
    decimalField
  }
}

object MoneyWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): Control = {
    val moneyField = new TextField()
    moneyField.setTextFormatter(
      TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(MONEY_MASK).buildDecimalConverter().build())
    moneyField
  }
}

object DateWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): Control = {
    val dateField = new DatePicker()
    val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    dateField.setConverter(new LocalDateStringConverter(dateFormatter, dateFormatter))
    dateField
  }
}

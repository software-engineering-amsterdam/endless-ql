package nl.uva.se.sc.niro.gui.factory

import java.time.LocalDate

import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.model.gui.GUIQuestion

trait WidgetFactory {
  val INTEGER_MASK = "\\d*"
  val DECIMAL_MASK = "\\d*(\\.\\d*)?"
  val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
  val DATE_FORMAT = "yyyy-MM-dd"
}

object BooleanWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): QLWidget[Boolean] = new QLBooleanField()
}

object StringWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): QLWidget[String] = new QLTextField()
}

object IntegerWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): QLWidget[java.lang.Integer] = new QLIntegerField()
}

object DecimalWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLDecimalField()
}

object MoneyWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLMoneyField()
}

object DateWidgetFactory extends WidgetFactory {
  def make(question: GUIQuestion): QLWidget[LocalDate] = new QLDateField()
}

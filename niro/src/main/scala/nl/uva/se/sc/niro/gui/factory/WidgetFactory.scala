package nl.uva.se.sc.niro.gui.factory

import java.time.format.DateTimeFormatter

import javafx.scene.control._
import javafx.util.converter.LocalDateStringConverter
import nl.uva.se.sc.niro.gui.builder.TextFormatterBuilder
import nl.uva.se.sc.niro.gui.factory.WidgetFactory.{ DATE_FORMAT, DECIMAL_MASK, INTEGER_MASK, MONEY_MASK }
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.model.gui.GUIQuestion

abstract class AbstractWidgetFactory {
  def make(question: GUIQuestion): Control
}

object WidgetFactory extends AbstractWidgetFactory {
  val INTEGER_MASK = "\\d*"
  val DECIMAL_MASK = "\\d*(\\.\\d*)?"
  val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
  val DATE_FORMAT = "yyyy-MM-dd"

  override def make(question: GUIQuestion): Control = {
    question.answerType match {
      case BooleanType => BooleanWidgetFactory.make(question)
      case StringType  => StringWidgetFactory.make(question)
      case IntegerType => IntegerWidgetFactory.make(question)
      case DecimalType => DecimalWidgetFactory.make(question)
      case MoneyType   => MoneyWidgetFactory.make(question)
      case DateType    => DateWidgetFactory.make(question)
    }
  }
}

object BooleanWidgetFactory extends AbstractWidgetFactory {
  def make(question: GUIQuestion): Control = {
    new CheckBox()
  }
}

object StringWidgetFactory extends AbstractWidgetFactory {
  def make(question: GUIQuestion): Control = {
    new TextField()
  }
}

object IntegerWidgetFactory extends AbstractWidgetFactory {
  def make(question: GUIQuestion): Control = {
    val integerField = new TextField()
    integerField.setTextFormatter(
      TextFormatterBuilder[Int]().buildInputFilter(INTEGER_MASK).buildIntegerConverter().build())
    integerField
  }
}

object DecimalWidgetFactory extends AbstractWidgetFactory {
  def make(question: GUIQuestion): Control = {
    val decimalField = new TextField()
    decimalField.setTextFormatter(
      TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(DECIMAL_MASK).buildDecimalConverter().build())
    decimalField
  }
}

object MoneyWidgetFactory extends AbstractWidgetFactory {
  def make(question: GUIQuestion): Control = {
    val moneyField = new TextField()
    moneyField.setTextFormatter(
      TextFormatterBuilder[java.math.BigDecimal]().buildInputFilter(MONEY_MASK).buildDecimalConverter().build())
    moneyField
  }
}

object DateWidgetFactory extends AbstractWidgetFactory {
  def make(question: GUIQuestion): Control = {
    val dateField = new DatePicker()
    val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    dateField.setConverter(new LocalDateStringConverter(dateFormatter, dateFormatter))
    dateField
  }
}

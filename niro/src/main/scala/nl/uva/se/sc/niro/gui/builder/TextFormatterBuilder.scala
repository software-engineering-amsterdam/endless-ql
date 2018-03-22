package nl.uva.se.sc.niro.gui.builder

import java.util.function.UnaryOperator

import javafx.scene.control.TextFormatter
import javafx.util.StringConverter
import javafx.util.converter.{ BigDecimalStringConverter, IntegerStringConverter }

class TextFormatterBuilder[T] {
  private var converter: StringConverter[_] = _
  private var inputFilter: UnaryOperator[TextFormatter.Change] = _
  private var defaultValue: T = _

  def buildInputFilter(pattern: String): TextFormatterBuilder[T] = {
    inputFilter = (change: TextFormatter.Change) => if (change.getControlNewText.matches(pattern)) change else null
    this
  }

  def buildIntegerConverter(): TextFormatterBuilder[T] = {
    converter = new IntegerStringConverter()
    this
  }

  def buildDecimalConverter(): TextFormatterBuilder[T] = {
    converter = new BigDecimalStringConverter()
    this
  }

  def buildDefaultValue(defaultValue: T): TextFormatterBuilder[T] = {
    this.defaultValue = defaultValue
    this
  }

  def build(): TextFormatter[T] =
    new TextFormatter[T](converter.asInstanceOf[StringConverter[T]], defaultValue, inputFilter)
}

object TextFormatterBuilder {
  def apply[T]() = new TextFormatterBuilder[T]()
}

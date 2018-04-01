package nl.uva.se.sc.niro.ql.view.format

import java.util.function.UnaryOperator

import javafx.scene.control.TextFormatter
import javafx.util.StringConverter
import javafx.util.converter.{ BigDecimalStringConverter, BigIntegerStringConverter }

abstract class TextFormatterBuilder[T] {
  protected var converter: StringConverter[T] = _
  private var inputFilter: UnaryOperator[TextFormatter.Change] = _
  private var defaultValue: T = _

  def buildInputFilter(pattern: String): TextFormatterBuilder[T] = {
    inputFilter = (change: TextFormatter.Change) => if (change.getControlNewText.matches(pattern)) change else null
    this
  }

  def buildDefaultValue(defaultValue: T): TextFormatterBuilder[T] = {
    this.defaultValue = defaultValue
    this
  }

  def buildConverter(): TextFormatterBuilder[T]

  def build(): TextFormatter[T] =
    new TextFormatter[T](converter, defaultValue, inputFilter)
}

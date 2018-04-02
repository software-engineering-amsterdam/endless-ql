package nl.uva.se.sc.niro.ql.view.format

import javafx.util.converter.BigIntegerStringConverter

class IntegerFormatterBuilder extends TextFormatterBuilder[java.math.BigInteger] {
  override def buildConverter(): TextFormatterBuilder[java.math.BigInteger] = {
    converter = new BigIntegerStringConverter()
    this
  }
}

object IntegerFormatterBuilder {
  def apply() = new IntegerFormatterBuilder()
}

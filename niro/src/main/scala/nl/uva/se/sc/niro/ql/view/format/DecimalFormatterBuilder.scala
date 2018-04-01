package nl.uva.se.sc.niro.ql.view.format

import javafx.util.converter.BigDecimalStringConverter

class DecimalFormatterBuilder extends TextFormatterBuilder[java.math.BigDecimal] {
  override def buildConverter(): TextFormatterBuilder[java.math.BigDecimal] = {
    converter = new BigDecimalStringConverter()
    this
  }
}

object DecimalFormatterBuilder {
  def apply() = new DecimalFormatterBuilder()
}

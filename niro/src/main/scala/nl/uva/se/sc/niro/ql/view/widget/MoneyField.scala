package nl.uva.se.sc.niro.ql.view.widget

import nl.uva.se.sc.niro.ql.view.format.{ DecimalFormatterBuilder, FormatMasks }

class MoneyField() extends AbstractTextField[java.math.BigDecimal] with FormatMasks {
  private val decimalFormatter = DecimalFormatterBuilder().buildInputFilter(MONEY_MASK).buildConverter().build()
  setTextFormatter(decimalFormatter)

  override def value(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)

  override def value: java.math.BigDecimal = decimalFormatter.getValue
}

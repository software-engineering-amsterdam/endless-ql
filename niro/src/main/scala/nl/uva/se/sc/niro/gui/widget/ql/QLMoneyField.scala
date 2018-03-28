package nl.uva.se.sc.niro.gui.widget.ql

import nl.uva.se.sc.niro.gui.builder.DecimalFormatterBuilder

class QLMoneyField() extends AbstractQLTextField[java.math.BigDecimal] {
  val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
  private val decimalFormatter = DecimalFormatterBuilder().buildInputFilter(MONEY_MASK).buildConverter().build()
  setTextFormatter(decimalFormatter)
  override def value(value: java.math.BigDecimal): Unit = decimalFormatter.setValue(value)
  override def value: java.math.BigDecimal = decimalFormatter.getValue
}

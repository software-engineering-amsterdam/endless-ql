package nl.uva.se.sc.niro.ql.view.widget

import nl.uva.se.sc.niro.ql.view.format.{ FormatMasks, IntegerFormatterBuilder }

class IntegerField() extends AbstractTextField[java.math.BigInteger] with FormatMasks {
  private val integerFormatter =
    IntegerFormatterBuilder().buildInputFilter(INTEGER_MASK).buildConverter().build()
  setTextFormatter(integerFormatter)
  override def value(value: java.math.BigInteger): Unit = integerFormatter.setValue(value)
  override def value: java.math.BigInteger = integerFormatter.getValue
}

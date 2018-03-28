package nl.uva.se.sc.niro.gui.widget.ql

import nl.uva.se.sc.niro.gui.widget.{ FormatMasks, IntegerFormatterBuilder }

class QLIntegerField() extends AbstractQLTextField[java.math.BigInteger] with FormatMasks {
  private val integerFormatter =
    IntegerFormatterBuilder().buildInputFilter(INTEGER_MASK).buildConverter().build()
  setTextFormatter(integerFormatter)
  override def value(value: java.math.BigInteger): Unit = integerFormatter.setValue(value)
  override def value: java.math.BigInteger = integerFormatter.getValue
}

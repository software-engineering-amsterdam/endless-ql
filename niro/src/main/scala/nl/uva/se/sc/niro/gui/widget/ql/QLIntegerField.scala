package nl.uva.se.sc.niro.gui.widget.ql

import nl.uva.se.sc.niro.gui.builder.IntegerFormatterBuilder

class QLIntegerField() extends AbstractQLTextField[java.math.BigInteger] {
  val INTEGER_MASK = "\\d*"
  private val integerFormatter =
    IntegerFormatterBuilder().buildInputFilter(INTEGER_MASK).buildConverter().build()
  setTextFormatter(integerFormatter)
  override def value(value: java.math.BigInteger): Unit = integerFormatter.setValue(value)
  override def value: java.math.BigInteger = integerFormatter.getValue
}

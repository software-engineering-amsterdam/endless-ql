package nl.uva.se.sc.niro.ql.view.format

trait FormatMasks {
  val INTEGER_MASK = "\\d*"
  val DECIMAL_MASK = "\\d*(\\.\\d*)?"
  val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
}

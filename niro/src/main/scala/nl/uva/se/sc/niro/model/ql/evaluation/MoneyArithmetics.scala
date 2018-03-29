package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

trait MoneyCanDoArithmetics {
  def moneyPlus(x: MoneyAnswer, y: Answer): MoneyAnswer = y match {
    case m: MoneyAnswer => MoneyAnswer(x.value + m.value)
    case _                => throw new UnsupportedOperationException(s"Can't perform operation $x + $y")
  }
  def moneySubtract(x: MoneyAnswer, y: Answer): MoneyAnswer = y match {
    case m: MoneyAnswer => MoneyAnswer(x.value - m.value)
    case _                => throw new UnsupportedOperationException(s"Can't perform operation $x - $y")
  }
  def moneyMultiply(x: MoneyAnswer, y: Answer): MoneyAnswer = y match {
    case i: IntegerAnswer => MoneyAnswer(x.value * BigDecimal(i.value))
    case d: DecimalAnswer => MoneyAnswer(x.value * d.value)
    case _                => throw new UnsupportedOperationException(s"Can't perform operation $x * $y")
  }
  def moneyMultiply(x: IntegerAnswer, y: Answer): MoneyAnswer = y match {
    case m: MoneyAnswer => moneyMultiply(m, x)
    case _                => throw new UnsupportedOperationException(s"Can't perform operation $x * $y")
  }
  def moneyMultiply(x: DecimalAnswer, y: Answer): MoneyAnswer = y match {
    case m: MoneyAnswer => moneyMultiply(m, x)
    case _                => throw new UnsupportedOperationException(s"Can't perform operation $x * $y")
  }
  def moneyDivide(x: MoneyAnswer, y: Answer): Answer = y match {
    case m: MoneyAnswer   => DecimalAnswer(x.value / m.value)
    case i: IntegerAnswer => MoneyAnswer(x.value / BigDecimal(i.value))
    case d: DecimalAnswer => MoneyAnswer(x.value / d.value)
    case _                => throw new UnsupportedOperationException(s"Can't perform operation $x / $y")
  }
  def moneyNegate(x: MoneyAnswer): MoneyAnswer = MoneyAnswer(-x.value)
}

object MoneyArithmetics {
  implicit object MoneyCanDoArithmetics extends MoneyCanDoArithmetics
}

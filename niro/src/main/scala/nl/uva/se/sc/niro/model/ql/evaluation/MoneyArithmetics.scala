package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

trait MoneyCanDoArithmetics {
  def moneyPlus(x: MoneyAnswer, y: Answer): MoneyAnswer = y match {
    case m: MoneyAnswer => MoneyAnswer(x.combine(m)(_ + _))
  }
  def moneySubtract(x: MoneyAnswer, y: Answer): MoneyAnswer = y match {
    case m: MoneyAnswer => MoneyAnswer(x.combine(m)(_ - _))
  }
  def moneyMultiply(x: MoneyAnswer, y: Answer): MoneyAnswer = y match {
    case i: IntegerAnswer => MoneyAnswer(x.combine(i)(_ * BigDecimal(_)))
    case d: DecimalAnswer => MoneyAnswer(x.combine(d)(_ * _))
  }
  def moneyMultiply(x: IntegerAnswer, y: Answer): MoneyAnswer = y match { case m: MoneyAnswer => moneyMultiply(m, x) }
  def moneyMultiply(x: DecimalAnswer, y: Answer): MoneyAnswer = y match { case m: MoneyAnswer => moneyMultiply(m, x) }
  def moneyDivide(x: MoneyAnswer, y: Answer): Answer = y match {
    case m: MoneyAnswer   => DecimalAnswer(x.combine(m)(_ / _))
    case i: IntegerAnswer => MoneyAnswer(x.combine(i)(_ / BigDecimal(_)))
    case d: DecimalAnswer => MoneyAnswer(x.combine(d)(_ / _))
  }
  def moneyNegate(x: MoneyAnswer): MoneyAnswer = MoneyAnswer(x.possibleValue.map(-_))
}

object MoneyArithmetics {
  implicit object MoneyCanDoArithmetics extends MoneyCanDoArithmetics
}

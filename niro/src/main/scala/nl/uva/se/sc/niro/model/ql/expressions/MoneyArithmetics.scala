package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

trait MoneyCanDoArithmetics {
  def plus(x: MoneyAnswer, y: Answer): MoneyAnswer = y match { case m: MoneyAnswer  => MoneyAnswer(x.value + m.value) }
  def minus(x: MoneyAnswer, y: Answer): MoneyAnswer = y match { case m: MoneyAnswer => MoneyAnswer(x.value - m.value) }
  def times(x: MoneyAnswer, y: Answer): MoneyAnswer = y match {
    case i: IntegerAnswer => MoneyAnswer(x.value * i.value)
    case d: DecimalAnswer => MoneyAnswer(x.value * d.value)
  }
  def times(x: IntegerAnswer, y: Answer): MoneyAnswer = y match { case m: MoneyAnswer => times(m, x) }
  def times(x: DecimalAnswer, y: Answer): MoneyAnswer = y match { case m: MoneyAnswer => times(m, x) }
  def div(x: MoneyAnswer, y: Answer): Answer = y match {
    case m: MoneyAnswer   => DecimalAnswer(x.value / m.value)
    case i: IntegerAnswer => MoneyAnswer(x.value / i.value)
    case d: DecimalAnswer => MoneyAnswer(x.value / d.value)
  }
  def negate(x: MoneyAnswer): MoneyAnswer = MoneyAnswer(-x.value)
}

object MoneyArithmetics {
  implicit object MoneyCanDoArithmetics extends MoneyCanDoArithmetics
}

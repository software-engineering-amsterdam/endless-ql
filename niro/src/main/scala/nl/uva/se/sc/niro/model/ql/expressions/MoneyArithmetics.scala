package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

trait MoneyCanDoArithmetics {
  def plus(x: MoneyAnswer, y: MoneyAnswer): MoneyAnswer = MoneyAnswer(x.value + y.value)
  def minus(x: MoneyAnswer, y: MoneyAnswer): MoneyAnswer = MoneyAnswer(x.value - y.value)
  def times(x: MoneyAnswer, y: IntegerAnswer): MoneyAnswer = MoneyAnswer(x.value * y.value)
  def times(x: IntegerAnswer, y: MoneyAnswer): MoneyAnswer = times(y, x)
  def times(x: MoneyAnswer, y: DecimalAnswer): MoneyAnswer = MoneyAnswer(x.value * y.value)
  def times(x: DecimalAnswer, y: MoneyAnswer): MoneyAnswer = times(y, x)
  def div(x: MoneyAnswer, y: MoneyAnswer): DecimalAnswer = DecimalAnswer(x.value / y.value)
  def div(x: MoneyAnswer, y: IntegerAnswer): MoneyAnswer = MoneyAnswer(x.value / y.value)
  def div(x: MoneyAnswer, y: DecimalAnswer): MoneyAnswer = MoneyAnswer(x.value / y.value)
  def negate(x: MoneyAnswer): MoneyAnswer = MoneyAnswer(-x.value)
}

object MoneyArithmetics {
  implicit object MoneyCanDoArithmetics extends MoneyCanDoArithmetics
}
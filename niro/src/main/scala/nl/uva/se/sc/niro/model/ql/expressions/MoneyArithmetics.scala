package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

trait MoneyCanDoArithmetics {
  def plus(x: MoneyAnswer, y: MoneyAnswer): MoneyAnswer = MoneyAnswer(x.combine(y)(_ + _))
  def minus(x: MoneyAnswer, y: MoneyAnswer): MoneyAnswer = MoneyAnswer(x.combine(y)(_ - _))
  def times(x: MoneyAnswer, y: IntegerAnswer): MoneyAnswer = MoneyAnswer(x.combine(y)(_ * _))
  def times(x: IntegerAnswer, y: MoneyAnswer): MoneyAnswer = times(y, x)
  def times(x: MoneyAnswer, y: DecimalAnswer): MoneyAnswer = MoneyAnswer(x.combine(y)(_ * _))
  def times(x: DecimalAnswer, y: MoneyAnswer): MoneyAnswer = times(y, x)
  def div(x: MoneyAnswer, y: MoneyAnswer): DecimalAnswer = DecimalAnswer(x.combine(y)(_ / _))
  def div(x: MoneyAnswer, y: IntegerAnswer): MoneyAnswer = MoneyAnswer(x.combine(y)(_ / _))
  def div(x: MoneyAnswer, y: DecimalAnswer): MoneyAnswer = MoneyAnswer(x.combine(y)(_ / _))
  def negate(x: MoneyAnswer): MoneyAnswer = MoneyAnswer(x.possibleValue.map(-_))
}

object MoneyArithmetics {
  implicit object MoneyCanDoArithmetics extends MoneyCanDoArithmetics
}
package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.evaluation.Orderings.MoneyAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model.ql.evaluation.MoneyArithmetics.MoneyCanDoArithmetics._

final case class MoneyAnswer(possibleValue: Option[BigDecimal]) extends Answer {

  type T = BigDecimal

  override def plus(right: Answer): Answer = moneyPlus(this, right)
  override def subtract(right: Answer): Answer = moneySubtract(this, right)
  override def multiply(right: Answer): Answer = moneyMultiply(this, right)
  override def divide(right: Answer): Answer = moneyDivide(this, right)

  override def lessThan(right: Answer): Answer = this < right
  override def lessThanEquals(right: Answer): Answer = this <= right
  override def greaterThenEquals(right: Answer): Answer = this >= right
  override def greaterThen(right: Answer): Answer = this > right
  override def notEquals(right: Answer): Answer = this !== right
  override def equals(right: Answer): Answer = this === right
}

object MoneyAnswer {
  def apply() = new MoneyAnswer(None)
  def apply(value: BigDecimal) = new MoneyAnswer(Option(value))
  def apply(value: java.math.BigDecimal) = new MoneyAnswer(Option(value).map(BigDecimal(_)))
}

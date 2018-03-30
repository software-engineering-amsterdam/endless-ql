package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.evaluation.BasicArithmetics.MoneyAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.evaluation.Orderings.MoneyAnswerCanDoOrderings._

final case class MoneyAnswer(value: BigDecimal) extends Answer {

  type T = BigDecimal

  override def plus(right: Answer): Answer = this + right
  override def subtract(right: Answer): Answer = this - right
  override def multiply(right: Answer): Answer = this * right
  override def divide(right: Answer): Answer = this / right

  override def lessThan(right: Answer): Answer = this < right
  override def lessThanEquals(right: Answer): Answer = this <= right
  override def greaterThenEquals(right: Answer): Answer = this >= right
  override def greaterThen(right: Answer): Answer = this > right
  override def notEquals(right: Answer): Answer = this !== right
  override def equals(right: Answer): Answer = this === right
}

object MoneyAnswer {
  def apply(value: java.math.BigDecimal) = new MoneyAnswer(BigDecimal(value))
}

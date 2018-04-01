package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.evaluation.BasicArithmetics.DecAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.evaluation.Orderings.DecAnswerCanDoOrderings._

final case class DecimalAnswer(value: BigDecimal) extends Answer {

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

object DecimalAnswer {
  def apply(value: java.math.BigDecimal) = new DecimalAnswer(BigDecimal(value))
}
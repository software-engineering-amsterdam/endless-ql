package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.evaluation.Orderings.DecAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model.ql.evaluation.BasicArithmetics.DecAnswerCanDoBasicArithmetics._

final case class DecimalAnswer(possibleValue: Option[BigDecimal]) extends Answer {

  type T = BigDecimal

  def typeOf: AnswerType = DecimalType

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
  def apply() = new DecimalAnswer(None)
  def apply(value: BigDecimal) = new DecimalAnswer(Option(value))
  def apply(value: java.math.BigDecimal) = new DecimalAnswer(Option(value).map(BigDecimal(_)))
}
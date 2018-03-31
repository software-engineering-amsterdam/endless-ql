package nl.uva.se.sc.niro.ql.model.ast.expressions.answers

import nl.uva.se.sc.niro.model.ql.evaluation.BasicArithmetics.IntAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.ql.model.ast.evaluation.Orderings.IntAnswerCanDoOrderings._

final case class IntegerAnswer(value: BigInt) extends Answer {

  type T = BigInt

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

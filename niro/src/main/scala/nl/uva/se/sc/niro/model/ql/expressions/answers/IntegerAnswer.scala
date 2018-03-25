package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.evaluation.BasicArithmetics.IntAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.evaluation.Orderings.IntAnswerCanDoOrderings._

final case class IntegerAnswer(possibleValue: Option[Int]) extends Answer {

  type T = Int

  def typeOf: AnswerType = IntegerType

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

object IntegerAnswer {
  def apply() = new IntegerAnswer(None)
  def apply(value: Int) = new IntegerAnswer(Option(value))
  def apply(value: java.lang.Integer) = new IntegerAnswer(Option(value).map(_.toInt))
}

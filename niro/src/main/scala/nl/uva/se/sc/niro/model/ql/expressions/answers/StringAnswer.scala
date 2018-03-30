package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.evaluation.Orderings.StringAnswerCanDoOrderings._

final case class StringAnswer(value: String) extends Answer {

  type T = String

  override def lessThan(right: Answer): Answer = this < right
  override def lessThanEquals(right: Answer): Answer = this <= right
  override def greaterThenEquals(right: Answer): Answer = this >= right
  override def greaterThen(right: Answer): Answer = this > right
  override def notEquals(right: Answer): Answer = this !== right
  override def equals(right: Answer): Answer = this === right
}

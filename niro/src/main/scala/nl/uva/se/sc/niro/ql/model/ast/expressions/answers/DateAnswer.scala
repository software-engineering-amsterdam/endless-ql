package nl.uva.se.sc.niro.ql.model.ast.expressions.answers

import java.time.LocalDate

import nl.uva.se.sc.niro.ql.model.ast.evaluation.Orderings.DateAnswerCanDoOrderings._

final case class DateAnswer(value: LocalDate) extends Answer {

  type T = LocalDate

  override def lessThan(right: Answer): Answer = this < right
  override def lessThanEquals(right: Answer): Answer = this <= right
  override def greaterThenEquals(right: Answer): Answer = this >= right
  override def greaterThen(right: Answer): Answer = this > right
  override def notEquals(right: Answer): Answer = this !== right
  override def equals(right: Answer): Answer = this === right
}

object DateAnswer {
  def apply(value: String): DateAnswer = new DateAnswer(LocalDate.parse(value))
}

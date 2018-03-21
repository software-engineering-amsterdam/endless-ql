package nl.uva.se.sc.niro.model.ql.expressions.answers

import java.time.LocalDate

import nl.uva.se.sc.niro.model.ql.Operators.Operator
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.DateAnswerCanDoOrderings._

final case class DateAnswer(value: LocalDate) extends Answer {

  type T = LocalDate

  def typeOf: AnswerType = DateType

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: DateAnswer =>
      operator match {
        case Operators.Lt  => this < that
        case Operators.Lte => this <= that
        case Operators.Gte => this >= that
        case Operators.Gt  => this > that
        case Operators.Ne  => this !== that
        case Operators.Eq  => this === that
        case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer =
    throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
}

object DateAnswer {
  def apply(value: String): DateAnswer = new DateAnswer(LocalDate.parse(value))
}

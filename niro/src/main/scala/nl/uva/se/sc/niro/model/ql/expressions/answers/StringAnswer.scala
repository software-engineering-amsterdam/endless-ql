package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operators.Operator
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.StringAnswerCanDoOrderings._

final case class StringAnswer(value: String) extends Answer {

  type T = String

  def typeOf: AnswerType = StringType

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: StringAnswer =>
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

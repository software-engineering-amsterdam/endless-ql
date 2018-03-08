package nl.uva.se.sc.niro.model.expressions.answers

import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.model.expressions.Orderings.StringAnswerCanDoOrderings._

final case class StringAnswer(possibleValue: Option[String]) extends Answer {

  type T = String

  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: StringAnswer =>
      operator match {
        case Lt  => this < that
        case Lte => this <= that
        case Gte => this >= that
        case Gt  => this > that
        case Ne  => this !== that
        case Eq  => this === that
        case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: UnaryOperator): Answer =
    throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
}

object StringAnswer {
  def apply() = new StringAnswer(None)
  def apply(value: String) = new StringAnswer(Some(value))
}

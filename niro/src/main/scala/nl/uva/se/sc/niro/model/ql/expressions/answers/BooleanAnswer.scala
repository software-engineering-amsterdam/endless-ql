package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.Logicals.BooleanAnswerCanDoLogicals._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.BooleanAnswerCanDoOrderings._

final case class BooleanAnswer(value: Boolean) extends Answer {

  type T = Boolean

  // TODO remove
  override def isTrue: Boolean = value

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: BooleanAnswer =>
      operator match {
        case Lt  => this < that
        case Lte => this <= that
        case Gte => this >= that
        case Gt  => this > that
        case Ne  => this !== that
        case Eq  => this === that
        case And => this && that
        case Or  => this || that
        case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Neg => !this
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

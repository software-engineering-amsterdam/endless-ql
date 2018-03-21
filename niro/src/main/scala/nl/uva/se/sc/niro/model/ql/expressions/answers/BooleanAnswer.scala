package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operators._
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.Logicals.BooleanAnswerCanDoLogicals._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.BooleanAnswerCanDoOrderings._

final case class BooleanAnswer(value: Boolean) extends Answer {

  type T = Boolean

  def typeOf: AnswerType = BooleanType

  override def isTrue: Boolean = value

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: BooleanAnswer =>
      operator match {
        case Operators.Lt  => this < that
        case Operators.Lte => this <= that
        case Operators.Gte => this >= that
        case Operators.Gt  => this > that
        case Operators.Ne  => this !== that
        case Operators.Eq  => this === that
        case Operators.And => this && that
        case Operators.Or  => this || that
        case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Neg => !this
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operators.Operator
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.MoneyArithmetics.MoneyCanDoArithmetics._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.MoneyAnswerCanDoOrderings._

final case class MoneyAnswer(value: BigDecimal) extends Answer {

  type T = BigDecimal

  def typeOf: AnswerType = BooleanType

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: MoneyAnswer =>
      operator match {
        case Operators.Add => plus(this, that)
        case Operators.Sub => minus(this, that)
        case Operators.Div => div(this, that)
        case Operators.Lt  => this < that
        case Operators.Lte => this <= that
        case Operators.Gte => this >= that
        case Operators.Gt  => this > that
        case Operators.Ne  => this !== that
        case Operators.Eq  => this === that
        case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case that: IntegerAnswer =>
      operator match {
        case Operators.Mul => times(this, that)
        case Operators.Div => div(this, that)
        case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case that: DecimalAnswer =>
      operator match {
        case Operators.Mul => times(this, that)
        case Operators.Div => div(this, that)
        case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Operators.Sub => MoneyAnswer(-value)
    case _             => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object MoneyAnswer {
  def apply(value: java.math.BigDecimal) = new MoneyAnswer(BigDecimal(value))
}

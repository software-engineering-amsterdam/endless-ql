package nl.uva.se.sc.niro.model.ql.expressions.answers
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.MoneyAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.MoneyArithmetics.MoneyCanDoArithmetics._

final case class MoneyAnswer(possibleValue: Option[BigDecimal]) extends Answer {

  type T = BigDecimal

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: MoneyAnswer =>
      operator match {
        case Add => plus(this, that)
        case Sub => minus(this, that)
        case Div => div(this, that)
        case Lt  => this < that
        case Lte => this <= that
        case Gte => this >= that
        case Gt  => this > that
        case Ne  => this !== that
        case Eq  => this === that
        case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case that: IntegerAnswer =>
      operator match {
        case Mul => times(this, that)
        case Div => div(this, that)
        case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case that: DecimalAnswer =>
      operator match {
        case Mul => times(this, that)
        case Div => div(this, that)
        case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Sub => MoneyAnswer(possibleValue.map(-_))
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object MoneyAnswer {
  def apply() = new MoneyAnswer(None)
  def apply(value: BigDecimal) = new MoneyAnswer(Some(value))
}

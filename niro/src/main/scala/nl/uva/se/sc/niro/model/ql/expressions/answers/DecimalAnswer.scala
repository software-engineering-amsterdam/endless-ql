package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operators.Operator
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.BasicArithmetics.DecAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.expressions.ImplicitConversions._
import nl.uva.se.sc.niro.model.ql.expressions.MoneyArithmetics.MoneyCanDoArithmetics.{ times => moneyTimes }
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.DecAnswerCanDoOrderings._

final case class DecimalAnswer(value: BigDecimal) extends Answer {

  type T = BigDecimal

  def typeOf: AnswerType = DecimalType

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: DecimalAnswer => applyDecimal(operator, that)
    case that: IntegerAnswer => applyDecimal(operator, that)
    case that: MoneyAnswer   => applyMoney(operator, that)
    case _                   => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Operators.Sub => DecimalAnswer(-value)
    case _             => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }

  private def applyMoney(operator: Operator, that: MoneyAnswer) = operator match {
    case Operators.Mul => moneyTimes(this, that)
    case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
  }

  private def applyDecimal(operator: Operator, that: DecimalAnswer) = operator match {
    case Operators.Add => this + that
    case Operators.Sub => this - that
    case Operators.Mul => this * that
    case Operators.Div => this / that
    case Operators.Lt  => this < that
    case Operators.Lte => this <= that
    case Operators.Gte => this >= that
    case Operators.Gt  => this > that
    case Operators.Ne  => this !== that
    case Operators.Eq  => this === that
    case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
  }
}

object DecimalAnswer {
  def apply(value: java.math.BigDecimal) = new DecimalAnswer(BigDecimal(value))
}

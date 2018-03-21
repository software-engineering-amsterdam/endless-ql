package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operators.Operator
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.BasicArithmetics.IntAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.expressions.MoneyArithmetics.MoneyCanDoArithmetics.{ times => moneyTimes }
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.IntAnswerCanDoOrderings._

final case class IntegerAnswer(value: Int) extends Answer {

  type T = Int

  def typeOf: AnswerType = IntegerType

  def toDecAnswer = DecimalAnswer(BigDecimal(value))

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: IntegerAnswer => applyInteger(operator, that)
    case that: DecimalAnswer => toDecAnswer.applyBinaryOperator(operator, that)
    case that: MoneyAnswer   => applyMoney(operator, that)
    case _                   => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  private def applyMoney(operator: Operator, that: MoneyAnswer) = {
    operator match {
      case Operators.Mul => moneyTimes(this, that)
      case _             => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Operators.Sub => -this
    case _             => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }

  private def applyInteger(operator: Operator, that: IntegerAnswer) = operator match {
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

object IntegerAnswer {
  def apply(value: java.lang.Integer) = new IntegerAnswer(value.toInt)
}

package nl.uva.se.sc.niro.model.ql.expressions.answers

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.BasicArithmetics.IntAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.expressions.MoneyArithmetics.MoneyCanDoArithmetics.{ times => moneyTimes }
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.IntAnswerCanDoOrderings._

final case class IntegerAnswer(value: Int) extends Answer {

  type T = Int

  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = IntegerType.asRight

  def toDecAnswer = DecimalAnswer(BigDecimal(value))

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: IntegerAnswer => applyInteger(operator, that)
    case that: DecimalAnswer => toDecAnswer.applyBinaryOperator(operator, that)
    case that: MoneyAnswer   => applyMoney(operator, that)
    case _                   => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  private def applyMoney(operator: Operator, that: MoneyAnswer) = {
    operator match {
      case Mul => moneyTimes(this, that)
      case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Sub => -this
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }

  private def applyInteger(operator: Operator, that: IntegerAnswer) = operator match {
    case Add => this + that
    case Sub => this - that
    case Mul => this * that
    case Div => this / that
    case Lt  => this < that
    case Lte => this <= that
    case Gte => this >= that
    case Gt  => this > that
    case Ne  => this !== that
    case Eq  => this === that
    case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
  }
}

object IntegerAnswer {
  def apply(value: java.lang.Integer) = new IntegerAnswer(value.toInt)
}

package nl.uva.se.sc.niro.model.ql.expressions.answers

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.BasicArithmetics.DecAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.expressions.ImplicitConversions._
import nl.uva.se.sc.niro.model.ql.expressions.MoneyArithmetics.MoneyCanDoArithmetics.{ times => moneyTimes }
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.DecAnswerCanDoOrderings._

final case class DecimalAnswer(value: BigDecimal) extends Answer {

  type T = BigDecimal

  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = DecimalType.asRight

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: DecimalAnswer => applyDecimal(operator, that)
    case that: IntegerAnswer => applyDecimal(operator, that)
    case that: MoneyAnswer   => applyMoney(operator, that)
    case _                   => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Sub => DecimalAnswer(-value)
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }

  private def applyMoney(operator: Operator, that: MoneyAnswer) = operator match {
    case Mul => moneyTimes(this, that)
    case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
  }

  private def applyDecimal(operator: Operator, that: DecimalAnswer) = operator match {
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

object DecimalAnswer {
  def apply(value: java.math.BigDecimal) = new DecimalAnswer(BigDecimal(value))
}

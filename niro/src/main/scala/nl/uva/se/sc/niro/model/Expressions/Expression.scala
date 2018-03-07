package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }

abstract class Expression

abstract class Answer extends Expression {

  type T

  val possibleValue: Option[T]

  def applyUnaryOperator(operator: UnaryOperator): Answer

  def applyBinaryOperator(operator: BinaryOperator, right: Answer): Answer

  def isTrue: Boolean = false

  def combine[R](that: Answer)(f: (T, that.T) => R): Option[R] =
    for {
      thisValue <- possibleValue
      thatValue <- that.possibleValue
    } yield f(thisValue, thatValue)
}

final case class Reference(value: String) extends Expression

final case class BinaryOperation(binaryOperator: BinaryOperator, left: Expression, right: Expression) extends Expression

final case class UnaryOperation(unaryOperator: UnaryOperator, left: Expression) extends Expression

object Answer {
  def apply(answerType: String): Answer = answerType match {
    case "boolean" => BooleanAnswer()
    case "integer" => IntAnswer()
    case "string"  => StringAnswer()
    case "decimal" => DecAnswer()
    case "money"   => MoneyAnswer()
    case "date"    => DateAnswer()
    case _         => throw new IllegalArgumentException(s"Unsupported answer type: $answerType")
  }
}

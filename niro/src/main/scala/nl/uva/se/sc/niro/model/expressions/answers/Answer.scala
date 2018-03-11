package nl.uva.se.sc.niro.model.expressions.answers

import nl.uva.se.sc.niro.model.Operator
import nl.uva.se.sc.niro.model.expressions.Expression

abstract class Answer extends Expression {

  type T

  val possibleValue: Option[T]

  def applyUnaryOperator(operator: Operator): Answer

  def applyBinaryOperator(operator: Operator, right: Answer): Answer

  def isTrue: Boolean = false

  def combine[R](that: Answer)(f: (T, that.T) => R): Option[R] =
    for {
      thisValue <- possibleValue
      thatValue <- that.possibleValue
    } yield f(thisValue, thatValue)
}

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

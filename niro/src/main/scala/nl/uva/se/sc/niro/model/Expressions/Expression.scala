package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }


sealed abstract class Expression

abstract class Answer extends Expression {
  def applyUnaryOperator(operator: UnaryOperator): Answer

  def applyBinaryOperator(operator: BinaryOperator, right: Answer): Answer
}

final case class Reference(value: String) extends Expression

final case class BinaryOperation(binaryOperator: BinaryOperator, left: Expression, right: Expression) extends Expression

final case class UnaryOperation(unaryOperator: UnaryOperator, left: Expression) extends Expression

object Answer {
  def apply(answerType: String): Answer = answerType match {
    case "boolean" => BooleanAnswer(None)
    case "integer" => IntAnswer(None)
    case "string" => StringAnswer(None)
    case "decimal" => DecAnswer(None)
    case "money" => MoneyAnswer(None)
    case "date" => DateAnswer(None)
  }
}
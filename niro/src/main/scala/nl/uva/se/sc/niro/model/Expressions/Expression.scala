package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model.Operators._

object Expression {

  abstract class Expression
  abstract class Answer extends Expression {
    def apply(operator: UnaryOperator): Answer
    def apply(operator: BinaryOperator, right: Answer): Answer
  }

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

  case class BinaryOperation(binaryOperator: BinaryOperator, left: Expression, right: Expression) extends Expression
  case class UnaryOperation(unaryOperator: UnaryOperator, left: Expression) extends Expression

  def evaluate(expr: Expression): Answer = expr match {
    case answer: Answer => answer
    case UnaryOperation(operator: UnaryOperator, expression) => evaluate(expression).apply(operator)
    case BinaryOperation(operator: BinaryOperator, leftExpression, rightExpression) => evaluate(leftExpression).apply(operator, evaluate(rightExpression))
  }
}

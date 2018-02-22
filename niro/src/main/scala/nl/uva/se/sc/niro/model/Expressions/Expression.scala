package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }

object Expression {

  abstract class Expression
  abstract class Answer extends Expression {
    def applyUnaryOperator(operator: UnaryOperator): Answer
    def applyBinaryOperator(operator: BinaryOperator, right: Answer): Answer
  }

  case class Reference(value: String) extends Expression

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

  // TODO check if it's necessary to make this call tail recursive
  def evaluate(expr: Expression, symbolTable: Map[String, Expression]): Answer = expr match {
    case answer: Answer =>
      answer
    case Reference(questionIdentifier) =>
      evaluate(symbolTable(questionIdentifier), symbolTable)
    case UnaryOperation(operator: UnaryOperator, expression) =>
      val answer = evaluate(expression, symbolTable)
      answer.applyUnaryOperator(operator)
    case BinaryOperation(operator: BinaryOperator, leftExpression, rightExpression) =>
      val leftAnswer = evaluate(leftExpression, symbolTable)
      val rightAnswer = evaluate(rightExpression, symbolTable)
      leftAnswer.applyBinaryOperator(operator, rightAnswer)
  }
}

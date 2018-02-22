package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.Expressions._
import nl.uva.se.sc.niro.model.{ BinaryOperator, QLForm, UnaryOperator }

object Evaluator {
  // TODO check if it's necessary to make this call tail recursive
  def evaluateExpression(expr: Expression, symbolTable: Map[String, Expression]): Answer = expr match {
    case answer: Answer =>
      answer
    case Reference(questionIdentifier) =>
      evaluateExpression(symbolTable(questionIdentifier), symbolTable)
    case UnaryOperation(operator: UnaryOperator, expression) =>
      val answer = evaluateExpression(expression, symbolTable)
      answer.applyUnaryOperator(operator)
    case BinaryOperation(operator: BinaryOperator, leftExpression, rightExpression) =>
      val leftAnswer = evaluateExpression(leftExpression, symbolTable)
      val rightAnswer = evaluateExpression(rightExpression, symbolTable)
      leftAnswer.applyBinaryOperator(operator, rightAnswer)
  }

  def evaluateQLForm(qLForm: QLForm): QLForm = ???
}

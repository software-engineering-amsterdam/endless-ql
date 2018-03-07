package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.expressions._
import nl.uva.se.sc.niro.model.QLForm.SymbolTable
import nl.uva.se.sc.niro.model._

object Evaluator {

  def evaluate(qLForm: QLForm): QLForm = {
    val evaluatedStatements: Seq[Statement] =
      qLForm.statements.map(statement => evaluate(statement, qLForm.symbolTable))

    qLForm.copy(statements = evaluatedStatements)
  }

  def evaluate(statement: Statement, symbolTable: SymbolTable): Statement = {
    statement match {
      case q: Question    => evaluate(q, symbolTable)
      case c: Conditional => evaluate(c, symbolTable)
    }
  }

  def evaluate(question: Question, symbolTable: SymbolTable): Question = {
    val evaluatedAnswer = evaluateExpression(question.expression, symbolTable: SymbolTable)

    question.copy(answer = Some(evaluatedAnswer))
  }

  // Recursion is happening between evaluateStatement and evaluateConditional
  def evaluate(conditional: Conditional, symbolTable: SymbolTable): Conditional = {
    val evaluatedPredicate = evaluateExpression(conditional.predicate, symbolTable)
    val evaluatedThenStatements = conditional.thenStatements.map(statement => evaluate(statement, symbolTable))

    conditional.copy(answer = Option(evaluatedPredicate), thenStatements = evaluatedThenStatements)
  }

  def evaluateExpression(expr: Expression, symbolTable: SymbolTable): Answer = expr match {
    case answer: Answer        => answer
    case Reference(questionId) => evaluateExpression(symbolTable(questionId), symbolTable)
    case UnaryOperation(operator: UnaryOperator, expression) =>
      val answer = evaluateExpression(expression, symbolTable)
      answer.applyUnaryOperator(operator)

    case BinaryOperation(operator: BinaryOperator, leftExpression, rightExpression) =>
      val leftAnswer = evaluateExpression(leftExpression, symbolTable)
      val rightAnswer = evaluateExpression(rightExpression, symbolTable)
      leftAnswer.applyBinaryOperator(operator, rightAnswer)
  }
}

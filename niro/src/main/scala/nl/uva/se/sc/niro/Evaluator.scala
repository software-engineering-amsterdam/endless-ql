package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.Expressions._
import nl.uva.se.sc.niro.model.QLForm.SymbolTable
import nl.uva.se.sc.niro.model._

object Evaluator {

  def evaluateQLForm(qLForm: QLForm): QLForm = {
    val evaluatedStatements: Seq[Statement] = qLForm.statements.map(statement => evaluateStatement(statement, qLForm.symbolTable))

    qLForm.copy(statements = evaluatedStatements)
  }

  def evaluateStatement(statement: Statement, symbolTable: SymbolTable): Statement = {
    statement match {
      case q: Question => evaluateQuestion(q, symbolTable)
      case c: Conditional => evaluateConditional(c, symbolTable)
      case e: ErrorStatement => e
    }
  }

  def evaluateQuestion(question: Question, symbolTable: SymbolTable): Question = {
    val evaluatedAnswer = evaluateExpression(question.answer, symbolTable: SymbolTable)

    question.copy(answer = evaluatedAnswer)
  }

  // Recursion is happening between evaluateStatement and evaluateConditional
  def evaluateConditional(conditional: Conditional, symbolTable: SymbolTable): Conditional = {
    val evaluatedPredicate = evaluateExpression(conditional.predicate, symbolTable)
    val evaluatedThenStatements = conditional.thenStatements.map(statement => evaluateStatement(statement, symbolTable))

    conditional.copy(predicate = evaluatedPredicate, thenStatements = evaluatedThenStatements)
  }

  // TODO check if it's necessary to make this call tail recursive
  def evaluateExpression(expr: Expression, symbolTable: SymbolTable): Answer = expr match {
    case answer: Answer => answer
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

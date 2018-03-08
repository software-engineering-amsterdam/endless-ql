package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer

object Evaluator {

  type Dictionary = Map[String, Answer]

  def evaluate(qLForm: QLForm, dictionary: Dictionary): Dictionary = {
    evaluate(qLForm.statements, qLForm.symbolTable, dictionary)
  }

  def evaluate(statements: Seq[Statement], symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    if (statements.isEmpty) {
      dictionary
    } else {
      val newDictionary: Dictionary = evaluate(statements.head, symbolTable, dictionary)
      evaluate(statements.tail, symbolTable, newDictionary)
    }
  }

  def evaluate(statement: Statement, symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    statement match {
      case q: Question    => evaluate(q, symbolTable, dictionary)
      case c: Conditional => evaluate(c, symbolTable, dictionary)
    }
  }

  def evaluate(question: Question, symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    val expression = memoryLookup(question.id, question.expression, dictionary)
    val evaluatedAnswer = evaluateExpression(expression, symbolTable: SymbolTable, dictionary)
    if (evaluatedAnswer.possibleValue.isDefined) {
      dictionary + (question.id -> evaluatedAnswer)
    } else
      dictionary - question.id
  }

  // Recursion is happening between evaluateStatement and evaluateConditional
  def evaluate(conditional: Conditional, symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    evaluate(conditional.thenStatements, symbolTable, dictionary)
  }

  def evaluateExpression(expression: Expression, symbolTable: SymbolTable, dictionary: Dictionary): Answer =
    expression match {
      case answer: Answer => answer
      case Reference(questionId) =>
        evaluateExpression(
          memoryLookup(questionId, symbolTable.get(questionId).map(_.expression).getOrElse(dictionary(questionId)), dictionary),
          symbolTable,
          dictionary
        )
      case UnaryOperation(operator: Operator, leftExpression) =>
        val answer = evaluateExpression(leftExpression, symbolTable, dictionary)
        answer.applyUnaryOperator(operator)
      case BinaryOperation(operator: Operator, leftExpression, rightExpression) =>
        val leftAnswer = evaluateExpression(leftExpression, symbolTable, dictionary)
        val rightAnswer = evaluateExpression(rightExpression, symbolTable, dictionary)
        leftAnswer.applyBinaryOperator(operator, rightAnswer)
    }

  /**
    * Only expressions that are defined to be a variable (answer) will be retrieved from the dictionary if exist.
    * This is done so values of calculated fields won't be retrieved from the dictionary
    */
  private def memoryLookup(questionId: String, expression: Expression, dictionary: Dictionary) = expression match {
    case _: Answer => dictionary.getOrElse(questionId, expression)
    case _         => expression
  }
}

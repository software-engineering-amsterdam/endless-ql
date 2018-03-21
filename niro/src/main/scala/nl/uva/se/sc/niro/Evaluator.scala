package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.ExpressionEvaluator._
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
    val optionalExpression: Option[Expression] = question.expression.orElse(dictionary.get(question.id))
    val optionalAnswer: Option[Answer] = optionalExpression.flatMap(_.evaluate(symbolTable: SymbolTable, dictionary))
    optionalAnswer.fold(dictionary - question.id)(answer => dictionary + (question.id -> answer))
  }

  // Recursion is happening between evaluateStatement and evaluateConditional
  def evaluate(conditional: Conditional, symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    evaluate(conditional.thenStatements, symbolTable, dictionary)
  }
}

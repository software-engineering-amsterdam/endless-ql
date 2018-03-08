package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.ExpressionEvaluator._
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer

sealed trait Statement

case class Question(id: String, label: String, answerType: AnswerType, expression: Expression) extends Statement

case class Conditional(predicate: Expression, thenStatements: Seq[Statement]) extends Statement

object Statement {

  def collectAllQuestions(statements: Seq[Statement]): Seq[Question] = {
    statements.flatMap {
      case q: Question    => Seq(q)
      case c: Conditional => collectAllQuestions(c.thenStatements)
    }
  }

  def collectAllConditionals(statements: Seq[Statement]): Seq[Conditional] = {
    statements.flatMap {
      case _: Question    => Seq.empty
      case c: Conditional => Seq(c) ++ collectAllConditionals(c.thenStatements)
    }
  }

  def collectAllVisibleQuestions(statements: Seq[Statement], symbolTable: SymbolTable): Seq[Question] = {
    statements.flatMap {
      case q: Question => Seq(q)
      case c: Conditional if c.predicate.evaluate(symbolTable, Map.empty).isTrue =>
        collectAllVisibleQuestions(c.thenStatements, symbolTable)
    }
  }

  def collectAllExpressions(statements: Seq[Statement]): Seq[Expression] = {
    statements.flatMap {
      case q: Question    => Seq(q.expression)
      case c: Conditional => Seq(c.predicate) ++ collectAllExpressions(c.thenStatements)
    }
  }

  def saveAnswer(questionId: String, answer: Answer, statements: Seq[Statement]): Seq[Statement] = {
    statements.flatMap {
      case q: Question if q.id == questionId => Seq(q.copy(expression = answer))
      case q: Question                       => Seq(q)
      case c: Conditional                    => Seq(c.copy(thenStatements = saveAnswer(questionId, answer, c.thenStatements)))
    }
  }
}

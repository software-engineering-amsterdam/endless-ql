package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.model.Expressions.Expression

case class QLForm(formName: String, statements: Seq[Statement]) {
  val symbolTable: Map[String, Expression] = Statement.collectAllQuestions(statements).map(q => (q.id, q.answer)).toMap
  val visibleQuestions = Statement.collectAllVisibleQuestions(statements, symbolTable)
}

sealed trait Statement
case class Question(id: String, label: String, answer: Expression) extends Statement
case class Conditional(predicate: Expression, thenStatements: Seq[Statement]) extends Statement

object Statement {

  def collectAllQuestions(statements: Seq[Statement]): Seq[Question] = {
    statements.flatMap {
      case q: Question => Seq(q)
      case c: Conditional => collectAllQuestions(c.thenStatements)
    }
  }

  def collectAllVisibleQuestions(statements: Seq[Statement], symbolTable: Map[String, Expression]): Seq[Question] = {
    statements.collect {
      case q: Question => Seq(q)
      case c: Conditional if Evaluator.evaluateExpression(c.predicate, symbolTable).isTrue => collectAllVisibleQuestions(c.thenStatements, symbolTable)
    }.flatten
  }
}

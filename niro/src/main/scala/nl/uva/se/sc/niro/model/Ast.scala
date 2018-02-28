package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.model.Expressions.{ Answer, Expression }

case class QLForm(formName: String, statements: Seq[Statement]) {
  val symbolTable: Map[String, Expression] =
    Statement.collectAllQuestions(statements).map(q => (q.id, q.expression)).toMap

  def save(questionId: String, answer: Answer): QLForm = {
    val updatedStatements = Statement.saveAnswer(questionId, answer, statements)
    QLForm(formName, updatedStatements)
  }
}

sealed trait Statement
case class ErrorStatement() extends Statement
case class Question(
    id: String,
    label: String,
    answerType: AnswerType,
    expression: Expression,
    answer: Option[Answer] = None)
    extends Statement

case class Conditional(predicate: Expression, thenStatements: Seq[Statement]) extends Statement

object Statement {

  def collectAllQuestions(statements: Seq[Statement]): Seq[Question] = {
    statements.flatMap {
      case q: Question      => Seq(q)
      case c: Conditional   => collectAllQuestions(c.thenStatements)
      case ErrorStatement() => Seq.empty
    }
  }

  def collectAllConditionals(statements: Seq[Statement]): Seq[Conditional] = {
    statements.flatMap {
      case q: Question      => Seq.empty
      case c: Conditional   => Seq(c) ++ collectAllConditionals(c.thenStatements)
      case ErrorStatement() => Seq.empty
    }
  }

  def collectAllVisibleQuestions(statements: Seq[Statement], symbolTable: Map[String, Expression]): Seq[Question] = {
    statements.flatMap {
      case q: Question => Seq(q)
      case c: Conditional if Evaluator.evaluateExpression(c.predicate, symbolTable).isTrue =>
        collectAllVisibleQuestions(c.thenStatements, symbolTable)
      case ErrorStatement() => Seq.empty
    }
  }

  def saveAnswer(questionId: String, answer: Answer, statements: Seq[Statement]): Seq[Statement] = {
    statements.flatMap {
      case q: Question if q.id == questionId => Seq(q.copy(answer = Some(answer)))
      case q: Question                       => Seq(q)
      case c: Conditional                    => saveAnswer(questionId, answer, c.thenStatements)
    }
  }
}

object QLForm {
  type SymbolTable = Map[String, Expression]
}

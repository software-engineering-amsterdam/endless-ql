package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.Expressions.Expression._

object Ast {

  case class QLForm(formName: String, statements: Seq[Statement]) {
    val symbolTable: Map[String, Expression] = Statement.collectQuestions(statements).map(q => (q.id, q.answer)).toMap
  }

  sealed trait Statement
  case class Question(id: String, label: String, answer: Expression) extends Statement
  case class Conditional(condition: Expression, ifStatements: Seq[Statement], elseStatements: Seq[Statement]) extends Statement

  object Statement {
    def collectQuestions(statements: Seq[Statement]): Seq[Question] = {
      statements.flatMap {
        case q: Question => Seq(q)
        case c: Conditional => collectQuestions(c.ifStatements) ++ collectQuestions(c.elseStatements)
      }
    }
  }
}

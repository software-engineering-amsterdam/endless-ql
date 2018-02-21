package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.Expressions.Expression._

object Ast {

  case class QLForm(formName: String, statements: Seq[Statement])

  sealed trait Statement
  case class Question(id: String, label: String, answer: Expression) extends Statement
  case class Conditional(condition: Expression, ifStatements: Seq[Statement], elseStatements: Seq[Statement]) extends Statement

}

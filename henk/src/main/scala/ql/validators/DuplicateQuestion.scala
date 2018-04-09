package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class DuplicateQuestionDeclaration(label: String) extends Exception(label)

class DuplicateQuestionValidator extends BaseValidator {
  def notEqualVarDecl(left: Statement, right: Statement): Boolean =
    (left, right) match {
      case (VarDecl(lrt, lid), VarDecl(rrt, rid)) => {
        lrt != rrt && lid == rid
      }
      case _ => false
    }

  def execute(ast: Root): Unit = {
    val statements = FormCollector.getStatements(ast)
    val questions = statements.flatMap(StatementCollector.getQuestions(_))

    questions.find(question => {
      !questions.filter(x => notEqualVarDecl(x.varDecl, question.varDecl))
        .isEmpty
    })
    .map(question => {
      val varDecl = question.varDecl
      val identifier = varDecl.id
      val message = s"""
        Question with label '${question.label}' and identifier 
        '${identifier.id}' is already declared with a different types 
        somewhere else in the form.
      """
      throw new DuplicateQuestionDeclaration(message)
    })
  }
}

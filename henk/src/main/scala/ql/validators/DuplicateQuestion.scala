package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class DuplicateQuestionDeclaration(label: String) extends Exception(label)

class DuplicateQuestionValidator extends BaseValidator {
  def check(ast: Statement): Option[Exception] = {
    val questions = StatementCollector.getQuestions(ast)

    questions.forEach { question =>
      {
        val duplicates =
          questions.filter(x => notEqualVarDecl(x.varDecl, question.varDecl))

        if (!duplicates.isEmpty) {
          return Some(new DuplicateQuestionDeclaration("already exist"))
        }
      }
    }
    None
  }

  def notEqualVarDecl(left: Statement, right: Statement): Boolean =
    (left, right) match {
      case (VarDecl(lrt, lid), VarDecl(rrt, rid)) => {
        lrt != rrt && lid == rid
      }
      case other => false
    }
}

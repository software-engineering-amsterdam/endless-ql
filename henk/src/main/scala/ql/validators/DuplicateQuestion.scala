package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

case class DuplicateQuestionDeclaration(label: String) extends Exception(label)

class DuplicateQuestionValidator extends BaseValidator {
  def execute(ast: ASTNode): Option[Exception] = {
    val questions = ASTCollector.getQuestions(ast)

    questions.forEach { question =>
      {
        val duplicates = questions.filter(x => notEqualTypeDecl(x.varDecl, question.varDecl))

        if (!duplicates.isEmpty) {
          return Some(new DuplicateQuestionDeclaration("already exist"))
        }
      }
    }
    None
  }

  def notEqualTypeDecl(left: ASTNode, right: ASTNode): Boolean =
    (left, right) match {
      case (ASTVarDecl(lrt, lid), ASTVarDecl(rrt, rid)) => {
        lrt != rrt && lid == rid
      }
      case other => false
    }
}

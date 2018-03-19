package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

case class DuplicateQuestionDeclaration(label: String) extends Exception(label)

object DuplicateQuestion {
  def check(ast: ASTNode): Option[Exception] = {
    val questions = ASTCollector.getQuestions(ast)

    questions.forEach { question =>
      {
        val duplicates = questions.collect {
          case q1: ASTQuestion
              if notEqualTypeDecl(q1.varDecl, question.varDecl) =>
            q1
        }

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

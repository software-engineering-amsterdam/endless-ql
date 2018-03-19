package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._
import ql.validators._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

class TypeChecker() {

  var error: Exception = null

  def checkValidators(node: ASTNode): Option[Exception] = {
    val validatorList = List(
      IdentifierValidator.check _,
      ConditionalValidator.check _,
      DuplicateQuestion.check _
    )

    val result = validatorList.flatMap(vc => {
      vc(node) match {
        case bv @ Some(ex: IdentifierNotDeclared) => {
          error = ex
          return bv
        }
        case bv @ Some(ex: ConditionalNotBoolean) => {
          error = ex
          return bv
        }
        case bv @ Some(ex: DuplicateQuestionDeclaration) => {
          error = ex
          return bv
        }
        case other => None
      }
    })
    None
  }

  def validate(node: ASTNode): Boolean = {
    checkValidators(node) match {
      case None => true
      case Some(_) => false
    }
  }
}

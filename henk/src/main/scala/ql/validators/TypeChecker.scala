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
    val validatorList = List(IdentifierValidator.check _, ConditionalValidator.check _)
    val result = validatorList.flatMap(vc => {
      vc(node) match {
        case bv @ Some(ex) => {
          error = ex
          return bv
        }
        case None => None
      }
    })
    None
  }

  def setError(exception: Throwable): Unit = {
    exception match {
      case ex: IdentifierNotDeclared => {
        error = ex
      }
      case ex: ConditionalNotBoolean => {
        error = ex
      }
    }
  }

  def validate(node: ASTNode): Boolean = {
    checkValidators(node) match {
      case None => true
      case Some(_) => false
    }
  }
}

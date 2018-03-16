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
          // errorMessage = s"Identifier with name '${ex.label}' is not declared!"
        }
        case None => None
      }
    })
    println(result)
    None

    // for {
      // nothing <- IdentifierValidator.check(node)
      // nothing <- ConditionalValidator.check(node)
    // } yield nothing
  }

  def setError(exception: Throwable): Unit = {
    exception match {
      case ex: IdentifierNotDeclared => {
        error = ex
        // errorMessage = s"Identifier with name '${ex.label}' is not declared!"
      }
      case ex: ConditionalNotBoolean => {
        error = ex
        // errorMessage = s"Identifier with name '${ex.label}' is not declared!"
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

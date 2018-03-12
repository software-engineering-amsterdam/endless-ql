package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._
import ql.validators._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

class TypeChecker(val node: ASTNode) {

  var errorMessage = ""

  def runValidators(): Try[Boolean] = {
    for {
      isValid <- IdentifierValidator.validate(node)
    } yield true
  }

  def setError(exception: Throwable): Unit = {
    exception match {
      case ex: IdentifierNotDeclared => {
        errorMessage = s"Identifier with name '${ex.label}' is not declared!"
      }
    }
  }

  def check(): Boolean = {
    runValidators() match {
      case Success(_) => true
      case Failure(ex) => {
        setError(ex)
        false
      }
    }
  }
}

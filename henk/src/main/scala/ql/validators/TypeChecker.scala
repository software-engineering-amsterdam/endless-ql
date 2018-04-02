package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._
import ql.validators._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

class TypeChecker() {

  var error: Option[Exception] = None
  var warnings: Option[List[String]] = None

  val validatorList: List[BaseValidator] = List(
    new IdentifierValidator(),
    new ConditionalValidator(),
    new DuplicateQuestionValidator(),
    new DuplicateLabelValidator(),
    new TypeInferenceValidator()
  )

  def verifyValidator(node: Statement, validator: BaseValidator): Boolean = {
    validator.check(node).map(ex => {
      ex match {
        case ex: IdentifierNotDeclared => {
          true
        }
        case ex: ConditionalNotBoolean => {
          true
        }
        case ex: DuplicateQuestionDeclaration => {
          true
        }
        case ex: DuplicateLabelDeclaration => {
          warnings = validator.getWarnings()
          false
        }
        case ex: InvalidTypeInfered => {
          true
        }
      }
    }).getOrElse(false)
  }

  def getExceptions(node: Statement): Option[Exception] = {
    validatorList.find(verifyValidator(node, _))
      .flatMap(ex => {
        error = ex.check(node)
        error
    })
  }

  def validate(ast: Statement): Boolean = {
    getExceptions(ast).isEmpty
  }
}

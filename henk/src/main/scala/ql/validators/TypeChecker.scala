package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._
import ql.validators._

import scala.collection.JavaConversions._

class TypeChecker() {

  var error: Exception = null
  var warnings: Option[List[String]] = None

  val validatorList: List[BaseValidator] = List(
    new IdentifierValidator(),
    new ConditionalValidator(),
    new DuplicateQuestionValidator(),
    new DuplicateLabelValidator(),
    new TypeInferenceValidator()
  )

  def checkValidators(node: Statement): Option[Exception] = {
    validatorList.map(vc => {
      vc.check(node) match {
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
        case Some(ex: DuplicateLabelDeclaration) => {
          warnings = vc.getWarnings()
        }
        case bv @ Some(ex: InvalidTypeInfered) => {
          error = ex
          return bv
        }
        case other => other
      }
    })
    None
  }

  def validate(node: Statement): Boolean = {
    checkValidators(node).map(_ => false) getOrElse(true)
  }
}

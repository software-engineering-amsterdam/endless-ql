package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._
import ql.validators._

import scala.collection.JavaConversions._
import scala.util.{Try, Success, Failure}

class TypeChecker {

  var error: Option[Exception] = None
  var warnings: Option[List[String]] = None

  val criticalValidators: List[BaseValidator] = List(
    new IdentifierValidator(),
    new ConditionalValidator(),
    new DuplicateQuestionValidator(),
    new TypeInferenceValidator()
  )
  
  val nonCriticalValidators: List[WarningValidator] = List(
    new DuplicateLabelValidator()
  )

  def runValidators(ast: Statement): Unit = {
    criticalValidators.forEach(_.execute(ast))
    nonCriticalValidators.find(!_.execute(ast))
      .map(failedValidator => {
        warnings = failedValidator.getWarnings
    })
  }

  def run(ast: Statement): Unit = {
    runValidators(ast)
  }
}

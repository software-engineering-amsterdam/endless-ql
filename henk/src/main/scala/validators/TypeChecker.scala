package general.validators

import ql.models.ast.{ Statement => QLStatement }
import qls.models.ast.{ Statement => QLSStatement }

import general.validators._

import scala.collection.JavaConversions._

class GeneralTypeChecker() {

  var error: Exception = null

  val validatorList: List[BaseValidator] = List(
    new GeneralIdentifierValidator(),
    new GeneralQuestionPlacementValidator(),
    new GeneralTypeCheckerValidator()
  )

  def checkValidators(ql: QLStatement, qls: QLSStatement): Option[Exception] = {
    validatorList.map(vc => {
      vc.check(ql, qls) match {
        case bv @ Some(ex: UndeclaredQuestionStyling) => {
          error = ex
          return bv
        }
        case bv @ Some(ex: IncompatibleTypes) => {
          error = ex
          return bv
        }
        case bv @ Some(ex: UnplacedQuestion) => {
          error = ex
          return bv
        }
        case other => other
      }
    })
    None
  }

  def validate(ql: QLStatement, qls: QLSStatement): Boolean = {
    checkValidators(ql, qls).map(_ => false) getOrElse(true)
  }
}

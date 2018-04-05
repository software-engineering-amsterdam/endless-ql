package general.validators

import ql.models.ast.{ Root => QLRoot }
import qls.models.ast.{ Statement => QLSStatement }

import general.validators._

import scala.collection.JavaConversions._

class GeneralTypeChecker {

  val validatorList: List[BaseValidator] = List(
    new GeneralIdentifierValidator(),
    new GeneralQuestionPlacementValidator(),
    new GeneralTypeCheckerValidator()
  )

  def runValidators(ql: QLRoot, qls: QLSStatement): Unit = {
    validatorList.forEach(_.execute(ql, qls))
  }

  def run(ql: QLRoot, qls: QLSStatement): Unit = {
    runValidators(ql, qls)
  }
}

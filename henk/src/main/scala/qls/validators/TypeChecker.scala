package qls.validators

import qls.models.ast._
import qls.validators._

import scala.collection.JavaConversions._

class TypeChecker() {

  val criticalValidators: List[QLSValidator] = List(
    new WidgetTypeCheckerValidator(),
    new DuplicateQuestionPlacementValidator()
  )

  def runValidators(ast: Root): Unit = {
    criticalValidators.forEach(_.execute(ast))
  }

  def run(ast: Root): Unit = {
    runValidators(ast)
  }
}

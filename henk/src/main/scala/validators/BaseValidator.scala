package general.validators

import ql.models.ast.{ Root => QLRoot }
import qls.models.ast.{ Statement => QLSStatement }

trait BaseValidator {
  def check(ql: QLRoot, qls: QLSStatement): Option[Exception]

  def getError(): Option[Exception] = {
    None
  }

  def getWarnings(): Option[List[String]] = {
    None
  }
}

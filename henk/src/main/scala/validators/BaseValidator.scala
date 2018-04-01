package general.validators

import ql.models.ast.{ Statement => QLStatement }
import qls.models.ast.{ Statement => QLSStatement }

trait BaseValidator {
  def check(ql: QLStatement, qls: QLSStatement): Option[Exception]

  def getError(): Option[Exception] = {
    None
  }

  def getWarnings(): Option[List[String]] = {
    None
  }
}

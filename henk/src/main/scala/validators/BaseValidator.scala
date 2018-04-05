package general.validators

import ql.models.ast.{ Root => QLast }
import qls.models.ast.{ Statement => QLSast }

trait BaseValidator {
  def execute(ql: QLast, qls: QLSast): Unit
}

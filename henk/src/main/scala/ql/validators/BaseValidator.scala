package ql.validators

import ql.models.ast._
abstract class Validator {
  def execute(ast: Statement): Any
}

trait BaseValidator extends Validator {
  def execute(ast: Statement): Unit
}

trait WarningValidator extends Validator {
  def execute(ast: Statement): Boolean

  def getWarnings(): Option[List[String]]
}

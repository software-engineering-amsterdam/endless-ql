package ql.validators

import ql.models.ast._
abstract class Validator {
  def execute(ast: Root): Any
}

trait BaseValidator extends Validator {
  def execute(ast: Root): Unit
}

trait WarningValidator extends Validator {
  def execute(ast: Root): Boolean

  def getWarnings(): Option[List[String]]
}

package ql.validators

import ql.models.ast._
// abstract class Validator {
  // def execute: Any
// }

trait BaseValidator {
  def execute(ast: Root): Unit
}

trait WarningValidator {
  def execute(ast: Root): Boolean

  def getWarnings(): Option[List[String]]
}

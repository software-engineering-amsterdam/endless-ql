package ql.validators

import ql.models.ast._

trait BaseValidator {
  def check(ast: Statement): Option[Exception]

  def getError(): Option[Exception] = {
    None
  }

  def getWarnings(): Option[List[String]] = {
    None
  }
}

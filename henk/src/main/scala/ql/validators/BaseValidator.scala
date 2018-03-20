package ql.validators

import ql.models.ast._

trait BaseValidator {
  def execute(ast: ASTNode): Option[Exception]
  def getError(): Option[Exception] = {
    None
  }

  def getWarnings(): Option[Exception] = {
    None
  }
}

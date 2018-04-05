package qls.validators

import ql.validators._

import qls.models.ast._

trait QLSValidator {
  def execute(ast: Root): Unit
}

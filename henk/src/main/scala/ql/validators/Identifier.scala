package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class IdentifierNotDeclared(label: String) extends Exception(label)

class IdentifierValidator extends BaseValidator {
  def execute(ast: Statement): Unit = {
    val declaredIdentifiers = StatementCollector
      .getVarDecls(ast)
      .flatMap(ExpressionCollector.getIdentifiers)

    val foundIdentifiers = ExpressionCollector.getIdentifiers(ast)

    foundIdentifiers.distinct.diff(declaredIdentifiers)
      .map(undeclaredIdentifier => {
        throw new IdentifierNotDeclared(undeclaredIdentifier.id)
      })
  }
}

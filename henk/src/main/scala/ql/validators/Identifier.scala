package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class IdentifierNotDeclared(label: String) extends Exception(label)

class IdentifierValidator extends BaseValidator {
  def check(ast: Statement): Option[Exception] = {
    val declaredIdentifiers = StatementCollector
      .getVarDecls(ast)
      .flatMap(ExpressionCollector.getIdentifiers)

    val foundIdentifiers = ExpressionCollector.getIdentifiers(ast)

    checkFound(foundIdentifiers, declaredIdentifiers)
  }

  def checkFound(found: List[Identifier],
                 declared: List[Identifier]): Option[Exception] = {
    for (identifier <- found) {
      if (!declared.contains(identifier)) {
        return Some(new IdentifierNotDeclared(identifier.id))
      }
    }
    None
  }
}

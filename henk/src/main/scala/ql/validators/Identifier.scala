package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

case class IdentifierNotDeclared(label: String) extends Exception(label)

class IdentifierValidator extends BaseValidator {
  def execute(ast: Root): Unit = {
    val declaredIdentifiers = getDeclaredIdentifiers(ast)
    val foundIdentifiers = getIdentifiers(ast)

    foundIdentifiers.distinct
      .diff(declaredIdentifiers)
      .map(undeclaredIdentifier => {
        throw new IdentifierNotDeclared(undeclaredIdentifier.id)
      })
  }

  def getDeclaredIdentifiers(ast: Root): List[Identifier] = {
    FormCollector
      .getStatements(ast)
      .flatMap(StatementCollector.getVarDecls)
      .flatMap(ExpressionCollector.getIdentifiers)
  }

  def getIdentifiers(ast: Root): List[Identifier] = {
    FormCollector
      .getStatements(ast)
      .flatMap(ExpressionCollector.getIdentifiers)
  }
}

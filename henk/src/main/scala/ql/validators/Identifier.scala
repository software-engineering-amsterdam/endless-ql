package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._

import scala.collection.JavaConversions._

case class IdentifierNotDeclared(label: String) extends Exception(label)

object IdentifierValidator {
  def validate(node: ASTNode): Unit = {
    val forms = ASTCollector.getFormBody(node)
    val formVarDecls = forms.map(ASTCollector.getVarDecls)
    val formTerminals = forms.map(ASTCollector.getTerminals)

    (formVarDecls, formTerminals).zipped.foreach(checkScope)
  }

  def checkScope(varDecls: List[ASTNode], terminals: List[ASTNode]): Unit = {
    val declaredIdentifiers =
      varDecls.map(ASTCollector.getIdentifiers).flatten
    terminals.foreach {
      case node: ASTIdentifier if !declaredIdentifiers.contains(node) => {
        throw new IdentifierNotDeclared(node.id)
      }
      case other => None
    }
  }
}

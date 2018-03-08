package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.visitors._
import ql.validators._

import scala.collection.JavaConversions._

// case class IdentifierNotDeclared(label: String) extends Exception(label)

class TypeChecker(val node: ASTNode) {

  var errorMessage = ""
  val ast = node

  def check(): Boolean = {
    try {
      IdentifierValidator.validate(ast)
      // checkConditions()
      true
    } catch {
      case ex: IdentifierNotDeclared => {
        errorMessage = s"Identifier with name '${ex.label}' is not declared!"
      }
      false
    }
  }

  // def checkVarDecls(): Unit = {
    // val forms = ASTCollector.getFormBody(ast)
    // val formVarDecls = forms.map(ASTCollector.getVarDecls)
    // val formTerminals = forms.map(ASTCollector.getTerminals)

    // (formVarDecls, formTerminals).zipped.foreach(checkScope)
  // }

  // def checkScope(varDecls: List[ASTNode], terminals: List[ASTNode]): Unit = {
    // val declaredIdentifiers =
      // varDecls.map(ASTCollector.getIdentifiers).flatten
    // terminals.foreach {
      // case node: ASTIdentifier if !declaredIdentifiers.contains(node) => {
        // throw new IdentifierNotDeclared(node.id)
      // }
      // case other => None
    // }
  // }
}

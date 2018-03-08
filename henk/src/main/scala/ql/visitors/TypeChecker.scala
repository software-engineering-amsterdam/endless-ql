package ql.visitors

import ql.models.ast._
import ql.parsers._

import scala.collection.JavaConversions._

case class IdentifierNotDeclared(label: String) extends Exception(label)

class TypeChecker(val node: ASTNode) {

  var errorMessage = ""
  val flattened = QlFormParser.flattenNT(node)
  val ast = node

  def check(): Boolean = {
    try {
      checkVarDecls()
      true
    } catch {
      case ex: IdentifierNotDeclared => {
        errorMessage = s"Identifier with name '${ex.label}' is not declared!"
      }
      false
    }
  }

  def collectFormBody(flat: List[ASTNode]): List[ASTNode] = {
    flat.collect { case body: ASTFormBody => body }
  }

  def checkVarDecls(): Unit = {
    val forms = collectFormBody(flattened)
    val formVarDecls = forms.map(QlFormParser.retrieveVarDecls)
    val formTerminals = forms.map(QlFormParser.retrieveTerminals)

    (formVarDecls, formTerminals).zipped.foreach(checkScope)
  }

  def checkScope(varDecls: List[ASTNode], terminals: List[ASTNode]): Unit = {
    val declaredIdentifiers =
      varDecls.map(QlFormParser.retrieveIdentifiers).flatten
    terminals.foreach {
      case node: ASTIdentifier if !declaredIdentifiers.contains(node) => {
        throw new IdentifierNotDeclared(node.id)
      }
      case other => None
    }
  }
}

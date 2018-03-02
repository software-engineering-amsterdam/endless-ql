package ql.visitors

import ql.models.ast._
import ql.parsers._

import scala.collection.JavaConversions._

case class IdentifierNotDeclared(label:String) extends Exception(label)

class TypeChecker(val node: ASTNode) {

  var errorMessage = ""

  // Change flatten implementation, we need to be able to call it with a block
  // so we could specify which nodes not to be flattened from appearing in the
  // result. One edge cases that needs this functionality is:
  //
  // _1. formheaders are the only place in which identifiers appear that are
  // allowed to be undeclared, ie no vardecl related.

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

  def checkVarDecls(): Unit = {
    val decls = flattened.collect { case ASTVarDecl(vd, id) => id }
    flattened.map {
      case node: ASTIdentifier if !decls.contains(node) => {
        throw new IdentifierNotDeclared(node.id)
      }
      case other => other
    }
  }
}

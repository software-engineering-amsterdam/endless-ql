package ql.visitors

import ql.models.ast._
import ql.parsers._

import scala.collection.JavaConversions._

object ASTCollector {
  def getFormBody(node: ASTNode): List[ASTNode] = {
    flattenNT(node).collect { case body: ASTFormBody => body }
  }

  def getIfStatement(node: ASTNode): List[ASTIfStatement] = {
    flattenNT(node).collect { case ifStmt:  ASTIfStatement => ifStmt }
  }

  def getTypeDecl(id: ASTIdentifier, ast: ASTNode): Option[ASTNode] = {
    val forms = getFormBody(ast)
    val formVarDecls = forms.flatMap(getVarDecls)
    val varDecl = formVarDecls.filter(vd => vd.id == id)
    if(varDecl.isEmpty) {
      None
    } else {
      Some(varDecl.get(0).typeDecl)
    }
  }

  def getTerminals(node: ASTNode): List[ASTNode] = {
    node match {
      case nt: ASTNonTerminal => traverseChildren(nt, getTerminals)
      case other              => List(other)
    }
  }

  def getIdentifiers(node: ASTNode): List[ASTIdentifier] = {
    node match {
      case nt: ASTNonTerminal => nt.flatten.flatMap(getIdentifiers)
      case id: ASTIdentifier  => List(id)
      case other              => List()
    }
  }

  def getVarDecls(node: ASTNode): List[ASTVarDecl] = {
    val flattened = flattenNT(node)
    flattened.collect { case varDecl: ASTVarDecl => varDecl }
  }

  // get ifConditionals from Root
  //   get non terminals -> collect conditionals
  // get identifiers without looking at formHeader
  //   get non terminals -> filter formHeader -> get terminals
  // get questions
  //   get non terminals -> collect questions
  // get identifier from vardec
  //   get non terminals -> collect vardec -> collect identifiers
  def traverseChildren(parent: ASTNode,
                       trav: (ASTNode) => List[ASTNode]): List[ASTNode] = {
    parent.flatten.map(trav).flatten
  }

  def flattenNT(node: ASTNode): List[ASTNode] = {
    node match {
      case nt: ASTNonTerminal => List(nt) ++ traverseChildren(nt, flattenNT)
      case other              => List()
    }
  }
}

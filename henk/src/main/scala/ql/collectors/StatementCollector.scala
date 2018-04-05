package ql.collectors

import ql.models.ast._
import ql.parsers._

object StatementCollector {
  def getExpressions(node: Statement): List[Expression] = {
    flattenStatements(node).collect {
      case ifStmt: IfStatement => ifStmt.expression
      case assign: ValAssign => assign.expression
      case decl: VarDecl => decl.id
    }
  }

  def getQuestions(node: Statement): List[Question] = {
    flattenStatements(node).collect { case q: Question => q }
  }

  def getIfStatements(node: Statement): List[IfStatement] = {
    flattenStatements(node).collect { case ifStmt: IfStatement => ifStmt }
  }

  def getElseStatements(node: Statement): List[ElseStatement] = {
    flattenStatements(node).collect { case elseStmt: ElseStatement => elseStmt }
  }

  def getVarDecls(node: Statement): List[VarDecl] = {
    flattenStatements(node).collect { case vardecl: VarDecl => vardecl }
  }

  def getComputations(node: Statement): List[Computation] = {
    flattenStatements(node).collect { case comp: Computation => comp }
  }

  def flattenStatements(node: Statement): List[Statement] = {
    node match {
      case question: Question =>
        List(question) ++ flattenStatements(question.varDecl)
      case comp: Computation => List(comp, comp.varDecl, comp.valAssign)
      case decl: VarDecl     => List(decl)
      case assign: ValAssign => List(assign)
      case cs: ConditionalStatement =>
        flattenStatements(cs.ifStmt) ++ cs.elseStmt.map(flattenStatements).getOrElse(List())
      case ifStmt: IfStatement =>
        List(ifStmt) ++ ifStmt.statements.flatMap(flattenStatements)
      case elseStmt: ElseStatement =>
        List(elseStmt) ++ elseStmt.statements.flatMap(flattenStatements)
    }
  }
}

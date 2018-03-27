package ql.collectors

import ql.models.ast._
import ql.parsers._

object StatementCollector {
  def getQuestions(node: Statement): List[Question] = {
    flattenStatements(node).collect { case q: Question => q }
  }

  def getIfStatements(node: Statement): List[IfStatement] = {
    flattenStatements(node).collect { case ifStmt: IfStatement => ifStmt }
  }

  def getVarDecls(node: Statement): List[VarDecl] = {
    flattenStatements(node).collect { case vardecl: VarDecl => vardecl }
  }

  def getComputations(node: Statement): List[Computation] = {
    flattenStatements(node).collect { case comp: Computation => comp }
  }

  def flattenStatements(node: Statement): List[Statement] = {
    node match {
      case root: Root         => List(root) ++ flattenStatements(root.body)
      case header: FormHeader => List(header)
      case body: FormBody =>
        List(body) ++ body.statements.flatMap(flattenStatements)
      case question: Question =>
        List(question) ++ flattenStatements(question.varDecl)
      case comp: Computation => List(comp, comp.varDecl, comp.valAssign)
      case decl: VarDecl     => List(decl)
      case assign: ValAssign => List(assign)
      case ifStmt: IfStatement =>
        List(ifStmt) ++ ifStmt.statements.flatMap(flattenStatements)
    }
  }
}

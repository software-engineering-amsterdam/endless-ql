package ql.collectors

import ql.models.ast._
import ql.parsers._

object ExpressionCollector {
  def getExpressions(node: Statement): List[Expression] = {
    StatementCollector.flattenStatements(node).collect {
      case ifStmt: IfStatement => ifStmt.expression
      case assign: ValAssign => assign.expression
      case decl: VarDecl => decl.id
    }
  }
}

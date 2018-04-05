package ql.collectors

import ql.models.ast._
import ql.collectors._

object TypeCollector {
  def getTypeDecl(id: Expression, ast: Root): Option[NodeType] = {
    val statements = FormCollector.getStatements(ast)
    val varDecls = statements.map(StatementCollector.getVarDecls(_)).flatten
    varDecls.find(vd => vd.id == id).map(_.typeDecl)
  }
}

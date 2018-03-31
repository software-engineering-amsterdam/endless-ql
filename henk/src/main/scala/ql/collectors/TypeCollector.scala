package ql.collectors

import ql.models.ast._
import ql.collectors._

object TypeCollector {
  def getTypeDecl(id: Expression, ast: Statement): Option[NodeType] = {
    val varDecls = StatementCollector.getVarDecls(ast)
    varDecls.find(vd => vd.id == id).map(_.typeDecl)
  }
}

package ql.collectors

import ql.models.ast._
import ql.parsers._

object ExpressionCollector {
  def getIdentifiers(statement: Statement): List[Identifier] = {
    StatementCollector.getExpressions(statement).collect {
      case id: Identifier => id
    }
  }
}

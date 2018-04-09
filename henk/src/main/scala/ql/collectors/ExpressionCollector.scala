package ql.collectors

import ql.models.ast._
import ql.parsers._

object ExpressionCollector {
  def getIdentifiers(statement: Statement): List[Identifier] = {
    StatementCollector.getExpressions(statement)
      .flatMap(flattenExpression).collect {
      case id: Identifier => id
    }
  }

  def flattenExpression(expression: Expression): List[Expression] = {
    expression match {
      case bo: BinaryOperand => List(bo) ++ flattenExpression(bo.rhs) ++ flattenExpression(bo.lhs)
      case uo: UnaryOperand => List(uo) ++ flattenExpression(uo.expr)
      case other => List(other)
    }
  }
}

package ql.validators

import ql.models.ast._
import ql.collectors._

import scala.collection.JavaConversions._

object ValidatorHelper {
  def matchExpressionType(expression: Expression, ntype: NodeType): Option[NodeType] = {
    (expression, ntype) match {
      case (bv: LogicalOperand, BooleanType()) => Some(BooleanType())
      case (bv: RelationalOperand, MoneyType()) => Some(BooleanType())
      case (bv: RelationalOperand, IntegerType()) => Some(BooleanType())
      case (bv: ArithmeticOperand, MoneyType()) => Some(MoneyType())
      case (bv: ArithmeticOperand, IntegerType()) => Some(IntegerType())
      case (bv: EqualityOperand, IntegerType()) => Some(BooleanType())
      case (bv: EqualityOperand, BooleanType()) => Some(BooleanType())
      case (bv: EqualityOperand, StringType()) => Some(BooleanType())
      case (bv: EqualityOperand, MoneyType()) => Some(BooleanType())
      case (bv: UnaryNotOp, BooleanType()) => Some(BooleanType())
      case (bv: UnaryMinOp, IntegerType()) => Some(IntegerType())
      case (bv: UnaryMinOp, MoneyType()) => Some(MoneyType())
      case other => None
    }
  }
  
  def infereBinary(expression: BinaryOperand, ast: Statement): Option[NodeType] = {
    val typeLeft = infereExpression(expression.lhs, ast)
    val typeRight = infereExpression(expression.rhs, ast)

    (typeLeft, typeRight) match {
      case (Some(lhs), Some(rhs)) if lhs == rhs => {
        matchExpressionType(expression, lhs)
      }
      case other => None
    }
  }

  def infereUnary(unaryOperand: UnaryOperand, ast: Statement): Option[NodeType] = {
    val expressionType = infereExpression(unaryOperand.expr, ast)
    expressionType.flatMap(expr => matchExpressionType(unaryOperand, expr))
  }

  def infereExpression(expression: Expression, ast: Statement): Option[NodeType] = {
    expression match {
      case IntegerValue(_) => Some(IntegerType())
      case BooleanValue(_) => Some(BooleanType())
      case StringValue(_) => Some(StringType())
      case bv @ Identifier(_) => TypeCollector.getTypeDecl(bv, ast)
      case bv: BinaryOperand => infereBinary(bv, ast)
      case bv: UnaryOperand => infereUnary(bv, ast)
    }
  }
}

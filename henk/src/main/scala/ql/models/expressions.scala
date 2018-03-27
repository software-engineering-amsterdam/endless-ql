package ql.models.ast

import ql.models._

sealed trait Expression

sealed trait BinaryOperand extends Expression {
  def rhs: Expression
  def lhs: Expression
}
sealed trait UnaryOperand extends Expression {
  def expr: Expression
}

sealed trait LogicalOperand extends BinaryOperand
sealed trait RelationalOperand extends BinaryOperand
sealed trait ArithmeticOperand extends BinaryOperand
sealed trait EqualityOperand extends BinaryOperand

case class LogicalConOp(rhs: Expression, lhs: Expression) extends LogicalOperand
case class LogicalDisOp(rhs: Expression, lhs: Expression) extends LogicalOperand

case class RelationalLTOp(rhs: Expression, lhs: Expression) extends RelationalOperand
case class RelationalLTEOp(rhs: Expression, lhs: Expression) extends RelationalOperand
case class RelationalGTOp(rhs: Expression, lhs: Expression) extends RelationalOperand
case class RelationalGTEOp(rhs: Expression, lhs: Expression) extends RelationalOperand

case class NotEqualOp(rhs: Expression, lhs: Expression) extends EqualityOperand
case class EqualOp(rhs: Expression, lhs: Expression) extends EqualityOperand

case class AddOp(rhs: Expression, lhs: Expression) extends ArithmeticOperand
case class MinOp(rhs: Expression, lhs: Expression) extends ArithmeticOperand
case class MulOp(rhs: Expression, lhs: Expression) extends ArithmeticOperand
case class DivOp(rhs: Expression, lhs: Expression) extends ArithmeticOperand

case class UnaryNotOp(expr: Expression) extends UnaryOperand
case class UnaryMinOp(expr: Expression) extends UnaryOperand

case class IntegerValue(value: Int) extends Expression
case class BooleanValue(value: Boolean) extends Expression
case class StringValue(value: String) extends Expression

case class Identifier(id: String) extends Expression

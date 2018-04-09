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

case class LogicalConOp(lhs: Expression, rhs: Expression) extends LogicalOperand
case class LogicalDisOp(lhs: Expression, rhs: Expression) extends LogicalOperand

case class RelationalLTOp(lhs: Expression, rhs: Expression) extends RelationalOperand
case class RelationalLTEOp(lhs: Expression, rhs: Expression) extends RelationalOperand
case class RelationalGTOp(lhs: Expression, rhs: Expression) extends RelationalOperand
case class RelationalGTEOp(lhs: Expression, rhs: Expression) extends RelationalOperand

case class NotEqualOp(lhs: Expression, rhs: Expression) extends EqualityOperand
case class EqualOp(lhs: Expression, rhs: Expression) extends EqualityOperand

case class AddOp(lhs: Expression, rhs: Expression) extends ArithmeticOperand
case class MinOp(lhs: Expression, rhs: Expression) extends ArithmeticOperand
case class MulOp(lhs: Expression, rhs: Expression) extends ArithmeticOperand
case class DivOp(lhs: Expression, rhs: Expression) extends ArithmeticOperand

case class UnaryNotOp(expr: Expression) extends UnaryOperand
case class UnaryMinOp(expr: Expression) extends UnaryOperand

case class Identifier(id: String) extends Expression

sealed trait ExpressionValue extends Expression {
  def hasNodeType: NodeType
  def value: Any
}
case class IntegerValue(value: Int) extends ExpressionValue {
  def hasNodeType = IntegerType

  def unary_- = IntegerValue(-this.value)
  def +(that: IntegerValue) = IntegerValue(this.value + that.value)
  def -(that: IntegerValue) = IntegerValue(this.value - that.value)
  def /(that: IntegerValue) = IntegerValue(this.value / that.value)
  def *(that: IntegerValue) = IntegerValue(this.value * that.value)

  def <(that: IntegerValue): Boolean = {
    this.value < that.value
  }

  def <=(that: IntegerValue): Boolean = {
    this.value <= that.value
  }

  def >(that: IntegerValue): Boolean = {
    this.value > that.value
  }

  def >=(that: IntegerValue): Boolean = {
    this.value >= that.value
  }

  def ==(that: IntegerValue): Boolean = {
    this.value == that.value
  }

  def !=(that: IntegerValue): Boolean = {
    this.value != that.value
  }
}

case class BooleanValue(value: Boolean) extends ExpressionValue {
  def hasNodeType = BooleanType

  def &&(that: BooleanValue): Boolean = {
    this.value && that.value
  }

  def ||(that: BooleanValue): Boolean = {
    this.value || that.value
  }

  def unary_! = BooleanValue(!this.value)
}
case class StringValue(value: String) extends ExpressionValue {
  def hasNodeType = StringType

  def ==(that: StringValue): Boolean = {
    this.value == that.value
  }
}

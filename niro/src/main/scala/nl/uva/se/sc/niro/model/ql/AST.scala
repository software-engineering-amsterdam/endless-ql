package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.model.ql.AST.ExprType.ExprType

object AST {

  sealed trait Node {
    def getChildren: Seq[Node]
  }

  object ExprType extends Enumeration {
    type ExprType = Value
    val String, Date, Bool, Integer, Decimal, Money = Value
  }

  sealed trait Expression extends Node {
    def exprType: ExprType
  }

  sealed trait Leaf extends Expression {
    override def getChildren: Seq[Node] = Seq.empty
  }
  case class Constant(exprType: ExprType, value: Any) extends Leaf
  case class Variable(name: String, exprType: ExprType) extends Leaf {
    private var v: Any = null
    def setValue(v: Any) = { this.v = v }
  }
  case class NamedExpression(name: String, expression: Expression) extends Leaf {
    override def exprType: ExprType = expression.exprType
  }

  sealed trait Operation extends Expression
  sealed trait Operator
  object UnaryOperator extends Enumeration with Operator {
    type UnaryOperator = Value
    val NEG, MIN = Value
  }

  sealed trait UnaryOperation extends Operation
  case class NegateOperation(op: UnaryOperation, expr: Expression) extends UnaryOperation {
    override def getChildren: Seq[Node] = Seq(expr)
    override def exprType: ExprType = expr.exprType
  }

  sealed trait BinaryOperator extends Operator
  object LogicalOperator extends Enumeration with BinaryOperator {
    type LogicalOperator = Value
    val AND, OR = Value
  }
  object ArithmeticOperator extends Enumeration with BinaryOperator {
    type LogicalOperator = Value
    val SUB, ADD, DIV, MUL = Value
  }
  object ComparisonOperator extends Enumeration with BinaryOperator {
    type LogicalOperator = Value
    val LT, LTE, GTE, GT, EQ, NE = Value
  }
  case class BinaryOperation(op: BinaryOperator, left: Expression, right: Expression) extends Operation {
    override def getChildren: Seq[Node] = Seq(left, right)
    override def exprType: ExprType = {
      op match {
        case LogicalOperator => ExprType.Bool
        case ComparisonOperator => ExprType.Bool
        case other => throw new IllegalArgumentException(s"Unsupported binary operation: $other")
      }
    }
  }

}

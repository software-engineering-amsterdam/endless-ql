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
  sealed trait UnaryOperation extends Operation
  case class NegateOperation(expr: Expression) extends UnaryOperation {
    override def getChildren: Seq[Node] = Seq(expr)
    override def exprType: ExprType = expr.exprType
  }

  abstract class BinaryOperation(left: Expression, right: Expression) extends Operation {
    override def getChildren: Seq[Node] = Seq(left, right)
  }
  abstract class LogicalOperation(left: Expression, right: Expression) extends BinaryOperation(left, right) {
    override def exprType: ExprType = { ExprType.Bool }
  }

}

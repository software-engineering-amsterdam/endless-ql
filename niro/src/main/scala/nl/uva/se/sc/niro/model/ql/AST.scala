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

  case class Form(name: String, statements: Seq[Statement]) extends Node {
    override def getChildren: Seq[Node] = statements
  }

  sealed trait Statement extends Node
  case class Question(label: String, answerValue: AnswerValue) extends Statement {
    override def getChildren: Seq[Node] = Seq(answerValue)
  }
  case class Conditional(condition: Expression, thenBlock: Seq[Statement], elseBlock: Seq[Statement]) extends Statement {
    override def getChildren: Seq[Node] = Seq(condition) ++ thenBlock ++ elseBlock
  }

  sealed trait Expression extends Node {
    def exprType: ExprType
  }

  sealed trait Leaf extends Expression {
    override def getChildren: Seq[Node] = Seq.empty
  }
  case class Constant(exprType: ExprType, value: Any) extends Leaf
  sealed trait AnswerValue extends Leaf
  sealed trait AnswerVariable extends AnswerValue
  case class VariableDefinition(name: String, exprType: ExprType) extends AnswerVariable
  case class VariableDeclaration(name: String, exprType: ExprType, expression: Expression) extends AnswerVariable
  case class Variable(name: String) extends AnswerValue {
    var answerVariable: AnswerVariable = null
    override def exprType: ExprType = answerVariable.exprType
  }

  // Operators
  sealed trait Operator
  object UnaryOperator extends Enumeration with Operator {
    type UnaryOperator = Value
    val NEG, MIN = Value
  }

  sealed trait BinaryOperator extends Operator
  object LogicalOperator extends Enumeration with BinaryOperator {
    type LogicalOperator = Value
    val AND, OR = Value
  }
  object ArithmeticOperator extends Enumeration with BinaryOperator {
    type ArithmeticOperator = Value
    val SUB, ADD, DIV, MUL = Value
  }
  object ComparisonOperator extends Enumeration with BinaryOperator {
    type ComparisonOperator = Value
    val LT, LTE, GTE, GT, EQ, NE = Value
  }

  // Operation expressions
  sealed trait Operation extends Expression

  sealed trait UnaryOperation extends Operation
  case class NegateOperation(op: UnaryOperation, expr: Expression) extends UnaryOperation {
    override def getChildren: Seq[Node] = Seq(expr)
    override def exprType: ExprType = expr.exprType
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

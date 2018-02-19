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

  sealed trait UnaryOperator extends Operator
  object UnaryOperators extends UnaryOperator {
    case class NEG() extends UnaryOperator
    case class MIN() extends UnaryOperator
  }

  sealed trait BinaryOperator extends Operator
  object LogicalOperator extends BinaryOperator {
    case class AND() extends BinaryOperator
    case class OR() extends BinaryOperator
  }
  object ArithmeticOperator extends BinaryOperator {
    case class SUB() extends BinaryOperator
    case class ADD() extends BinaryOperator
    case class DIV() extends BinaryOperator
    case class MUL() extends BinaryOperator
  }
  object ComparisonOperator extends BinaryOperator {
    case class LT() extends BinaryOperator
    case class LTE() extends BinaryOperator
    case class GTE() extends BinaryOperator
    case class GT() extends BinaryOperator
    case class EQ() extends BinaryOperator
    case class NE() extends BinaryOperator
  }

  // Operation expressions
  sealed trait Operation extends Expression

  case class UnaryOperation(op: UnaryOperator, expr: Expression) extends Operation {
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

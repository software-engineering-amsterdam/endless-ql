package nl.uva.se.sc.niro.parser.checks

import nl.uva.se.sc.niro.model.ql.AST._
import nl.uva.se.sc.niro.parser.{CheckException, TypeCheckException}

object TypeChecker {

  @throws[CheckException]
  def checkTypes(node: Node): Unit  = {
    node match {
      case question: Question => checkTypes(question)
      case conditional: Conditional => checkTypes(conditional)
      case binary: BinaryOperation => checkTypes(binary)
      case _ => node.getChildren.foreach(checkTypes)
    }
  }

  @throws[CheckException]
  def checkTypes(question: Question): Unit = {
    checkTypes(question.answerValue)
  }

  @throws[CheckException]
  def checkTypes(conditional: Conditional): Unit = {
    checkTypes(conditional.condition)
    if (conditional.condition.exprType != ExprType.Bool) {
      throw new TypeCheckException("Conditional expression resolves not to boolean!")
    }
    conditional.thenBlock.foreach(this.checkTypes)
    conditional.elseBlock.foreach(this.checkTypes)
  }

  @throws[CheckException]
  def checkTypes(binaryOperation: BinaryOperation): Unit = {
    checkTypes(binaryOperation.left)
    checkTypes(binaryOperation.right)
    binaryOperation.op match {
      case ArithmeticOperator.ADD | ArithmeticOperator.DIV | ArithmeticOperator.MUL | ArithmeticOperator.SUB => {
        (binaryOperation.left.exprType, binaryOperation.right.exprType) match {
          case (ExprType.String, _) => throw new TypeCheckException("The left hand side of the arithmetic expression yields a string!")
          case (ExprType.Date, _) => throw new TypeCheckException("The left hand side of the arithmetic expression yields a date!")
          case (ExprType.Bool, _) => throw new TypeCheckException("The left hand side of the arithmetic expression yields a boolean!")
          case (_, ExprType.String) => throw new TypeCheckException("The right hand side of the arithmetic expression yields a string!")
          case (_, ExprType.Date) => throw new TypeCheckException("The right hand side of the arithmetic expression yields a date!")
          case (_, ExprType.Bool) => throw new TypeCheckException("The right hand side of the arithmetic expression yields a boolean!")
          case (_, _) => () // Catch all for all legal arithmetic operations to suppress compiler warnings.
        }
      }
      case _ => () // Catch all to suppress compiler warnings.
    }
  }
}

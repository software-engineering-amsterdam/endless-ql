package nl.uva.se.sc.niro.parser.checks

import nl.uva.se.sc.niro.model.ql.AST.ExprType.ExprType
import nl.uva.se.sc.niro.model.ql.AST._
import nl.uva.se.sc.niro.parser.{CheckException, TypeCheckException}

object TypeChecker {

  @throws[CheckException]
  def checkTypes(node: Node): Unit  = {
    node match {
      case question: Question => checkTypes(question)
      case conditional: Conditional => checkTypes(conditional)
      case BinaryOperation(op: ArithmeticOperator, left, right) => checkTypes(BinaryOperation(op, left, right))
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
    def checkForNonNumericTypes(exprType: ExprType, side: String): Unit = {
      exprType match {
        case ExprType.String => throw new TypeCheckException(s"The $side hand side of the arithmetic expression yields a string!")
        case ExprType.Date => throw new TypeCheckException(s"The $side hand side of the arithmetic expression yields a date!")
        case ExprType.Bool => throw new TypeCheckException(s"The $side hand side of the arithmetic expression yields a boolean!")
        case _ => () // Catch all for all legal arithmetic operations to suppress compiler warnings.
      }
    }

    checkTypes(binaryOperation.left)
    checkForNonNumericTypes(binaryOperation.left.exprType, "left")

    checkTypes(binaryOperation.right)
    checkForNonNumericTypes(binaryOperation.right.exprType, "right")
  }
}

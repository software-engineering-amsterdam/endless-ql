package nl.uva.se.sc.niro.parser.checks

import nl.uva.se.sc.niro.model.ql.AST._
import nl.uva.se.sc.niro.parser.{CheckException, TypeCheckException}

object TypeChecker {

  def checkTypes(node: Node): Unit = {
    node match {
      case question: Question => checkTypes(question)
      case conditional: Conditional => checkTypes(conditional)
      case _ => node.getChildren.foreach(checkTypes)
    }
  }

  def checkTypes(question: Question): Unit = {
    checkTypes(question.answerValue)
  }

  def checkTypes(conditional: Conditional): Unit = {
    checkTypes(conditional.condition)
    if (conditional.condition.exprType != ExprType.Bool) {
      System.err.println("Type check error detected, conditional expression does not yield a boolean!")
      throw new TypeCheckException("Conditional expression resolves not to boolean!")
    }
    conditional.thenBlock.foreach(this.checkTypes)
    conditional.elseBlock.foreach(this.checkTypes)
  }
}

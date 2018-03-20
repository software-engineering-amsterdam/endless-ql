package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.Evaluator.Dictionary
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer
import nl.uva.se.sc.niro.model.ql.expressions.{ BinaryOperation, Expression, Reference, UnaryOperation }

object ExpressionEvaluator {
  implicit class ExpressionOps(e: Expression) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = e match {
      case a: Answer          => Some(a)
      case r: Reference       => r.evaluate(symbolTable, dictionary)
      case u: UnaryOperation  => u.evaluate(symbolTable, dictionary)
      case b: BinaryOperation => b.evaluate(symbolTable, dictionary)
    }
  }

  implicit class UnaryOps(u: UnaryOperation) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] =
      u.left.evaluate(symbolTable, dictionary).map(_.applyUnaryOperator(u.unaryOperator))
  }

  implicit class BinaryOps(b: BinaryOperation) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- b.left.evaluate(symbolTable, dictionary)
        rightAnswer <- b.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.applyBinaryOperator(b.binaryOperator, rightAnswer)
    }
  }

  implicit class ReferenceOps(r: Reference) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      symbolTable
        .get(r.questionId)
        .flatMap(_.expression)
        .orElse(dictionary.get(r.questionId))
        .flatMap(_.evaluate(symbolTable, dictionary))
    }
  }

  /**
    * Only expressions that are defined to be a variable (answer) will be retrieved from the dictionary if exist.
    * This is done so values of calculated fields won't be retrieved from the dictionary
    */
  private def memoryLookup(questionId: String, expression: Expression, dictionary: Dictionary) = expression match {
    case _: Answer => dictionary.getOrElse(questionId, expression)
    case _         => expression
  }
}

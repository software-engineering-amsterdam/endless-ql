package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.evaluation.QLFormEvaluator.Dictionary
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer

object ExpressionEvaluator {
  implicit class ExpressionOps(e: Expression) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = e match {
      case a: Answer           => Some(a)
      case r: Reference        => r.evaluate(symbolTable, dictionary)
      case a: Addition         => a.evaluate(symbolTable, dictionary)
      case s: Subtract         => s.evaluate(symbolTable, dictionary)
      case m: Multiply         => m.evaluate(symbolTable, dictionary)
      case d: Divide           => d.evaluate(symbolTable, dictionary)
      case m: Minus            => m.evaluate(symbolTable, dictionary)
      case l: LessThan         => l.evaluate(symbolTable, dictionary)
      case l: LessThanEqual    => l.evaluate(symbolTable, dictionary)
      case g: GreaterThenEqual => g.evaluate(symbolTable, dictionary)
      case g: GreaterThen      => g.evaluate(symbolTable, dictionary)
      case n: NotEqual         => n.evaluate(symbolTable, dictionary)
      case e: Equal            => e.evaluate(symbolTable, dictionary)
      case o: Or               => o.evaluate(symbolTable, dictionary)
      case a: And              => a.evaluate(symbolTable, dictionary)
      case n: Negate           => n.evaluate(symbolTable, dictionary)
    }
  }

  implicit class AdditionOps(expression: Addition) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.plus(rightAnswer)
    }
  }

  implicit class SubtractOps(expression: Subtract) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.subtract(rightAnswer)
    }
  }

  implicit class MultiplyOps(expression: Multiply) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.multiply(rightAnswer)
    }
  }

  implicit class DivideOps(expression: Divide) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.divide(rightAnswer)
    }
  }

  implicit class MinusOps(expression: Minus) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield rightAnswer.minus
    }
  }

  implicit class LessThanOps(expression: LessThan) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.lessThan(rightAnswer)
    }
  }

  implicit class LessThanEqualOps(expression: LessThanEqual) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.lessThanEquals(rightAnswer)
    }
  }

  implicit class GreaterThenEqualOps(expression: GreaterThenEqual) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.greaterThenEquals(rightAnswer)
    }
  }

  implicit class GreaterThenOps(expression: GreaterThen) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.greaterThen(rightAnswer)
    }
  }

  implicit class NotEqualOps(expression: NotEqual) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.notEquals(rightAnswer)
    }
  }

  implicit class EqualOps(expression: Equal) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.equals(rightAnswer)
    }
  }

  implicit class OrOps(expression: Or) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.or(rightAnswer)
    }
  }

  implicit class AndOps(expression: And) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, dictionary)
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.and(rightAnswer)
    }
  }

  implicit class NegateOps(expression: Negate) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        rightAnswer <- expression.right.evaluate(symbolTable, dictionary)
      } yield rightAnswer.negate
    }
  }

  implicit class ReferenceOps(expression: Reference) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      symbolTable
        .get(expression.questionId)
        .flatMap(_.expression)
        .orElse(dictionary.get(expression.questionId))
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

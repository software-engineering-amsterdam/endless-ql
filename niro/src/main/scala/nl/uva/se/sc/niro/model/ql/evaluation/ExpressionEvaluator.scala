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

  implicit class AdditionOps(a: Addition) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- a.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- a.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.plus(rightAnswer)
    }
  }

  implicit class SubtractOps(s: Subtract) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- s.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- s.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.subtract(rightAnswer)
    }
  }

  implicit class MultiplyOps(m: Multiply) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- m.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- m.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.multiply(rightAnswer)
    }
  }

  implicit class DivideOps(d: Divide) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- d.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- d.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.divide(rightAnswer)
    }
  }

  implicit class MinusOps(m: Minus) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- m.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.minus
    }
  }

  implicit class LessThanOps(l: LessThan) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- l.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- l.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.lessThan(rightAnswer)
    }
  }

  implicit class LessThanEqualOps(l: LessThanEqual) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- l.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- l.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.lessThanEquals(rightAnswer)
    }
  }

  implicit class GreaterThenEqualOps(g: GreaterThenEqual) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- g.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- g.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.greaterThenEquals(rightAnswer)
    }
  }

  implicit class GreaterThenOps(g: GreaterThen) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- g.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- g.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.greaterThen(rightAnswer)
    }
  }

  implicit class NotEqualOps(n: NotEqual) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- n.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- n.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.notEquals(rightAnswer)
    }
  }

  implicit class EqualOps(e: Equal) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- e.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- e.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.equals(rightAnswer)
    }
  }

  implicit class OrOps(e: Or) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- e.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- e.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.or(rightAnswer)
    }
  }

  implicit class AndOps(e: And) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- e.left.evaluate(symbolTable, dictionary)
        rightAnswer: Answer <- e.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.and(rightAnswer)
    }
  }

  implicit class NegateOps(n: Negate) {
    def evaluate(symbolTable: SymbolTable, dictionary: Dictionary): Option[Answer] = {
      for {
        leftAnswer: Answer <- n.right.evaluate(symbolTable, dictionary)
      } yield leftAnswer.negate
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

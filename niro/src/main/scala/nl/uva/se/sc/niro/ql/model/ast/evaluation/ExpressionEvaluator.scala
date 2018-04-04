package nl.uva.se.sc.niro.ql.model.ast.evaluation

import nl.uva.se.sc.niro.ql.model.ast.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.ql.model.ast.evaluation.QLFormEvaluator.ValueStore
import nl.uva.se.sc.niro.ql.model.ast.expressions.Widener._
import nl.uva.se.sc.niro.ql.model.ast.expressions._
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

object ExpressionEvaluator {
  implicit class ExpressionOps(e: Expression) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = e match {
      case a: Answer           => Some(a.evaluate)
      case r: Reference        => r.evaluate(symbolTable, valueStore)
      case a: Addition         => a.evaluate(symbolTable, valueStore)
      case s: Subtract         => s.evaluate(symbolTable, valueStore)
      case m: Multiply         => m.evaluate(symbolTable, valueStore)
      case d: Divide           => d.evaluate(symbolTable, valueStore)
      case m: Minus            => m.evaluate(symbolTable, valueStore)
      case l: LessThan         => l.evaluate(symbolTable, valueStore)
      case l: LessThanEqual    => l.evaluate(symbolTable, valueStore)
      case g: GreaterThenEqual => g.evaluate(symbolTable, valueStore)
      case g: GreaterThen      => g.evaluate(symbolTable, valueStore)
      case n: NotEqual         => n.evaluate(symbolTable, valueStore)
      case e: Equal            => e.evaluate(symbolTable, valueStore)
      case o: Or               => o.evaluate(symbolTable, valueStore)
      case a: And              => a.evaluate(symbolTable, valueStore)
      case n: Negate           => n.evaluate(symbolTable, valueStore)
    }
  }

  implicit class AnswerOps(expression: Answer) {
    def evaluate = expression
  }

  implicit class AdditionOps(expression: Addition) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.plus(widened._2)
    }
  }

  implicit class SubtractOps(expression: Subtract) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.subtract(widened._2)
    }
  }

  implicit class MultiplyOps(expression: Multiply) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.multiply(widened._2)
    }
  }

  implicit class AnswerCanBeZero(answer: Answer) {
    def isZero: Boolean = answer match {
      case IntegerAnswer(value) if value == 0 => true
      case DecimalAnswer(value) if value == 0 => true
      case MoneyAnswer(value) if value == 0   => true
      case _                                  => false
    }
  }

  implicit class DivideOps(expression: Divide) {

    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        if !rightAnswer.isZero
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.divide(widened._2)
    }
  }

  implicit class MinusOps(expression: Minus) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
      } yield rightAnswer.minus
    }
  }

  implicit class LessThanOps(expression: LessThan) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.lessThan(widened._2)
    }
  }

  implicit class LessThanEqualOps(expression: LessThanEqual) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.lessThanEquals(widened._2)
    }
  }

  implicit class GreaterThenEqualOps(expression: GreaterThenEqual) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.greaterThenEquals(widened._2)
    }
  }

  implicit class GreaterThenOps(expression: GreaterThen) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.greaterThen(widened._2)
    }
  }

  implicit class NotEqualOps(expression: NotEqual) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.notEquals(widened._2)
    }
  }

  implicit class EqualOps(expression: Equal) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
        widened = widen(leftAnswer, rightAnswer)
      } yield widened._1.equals(widened._2)
    }
  }

  implicit class OrOps(expression: Or) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
      } yield leftAnswer.or(rightAnswer)
    }
  }

  implicit class AndOps(expression: And) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        leftAnswer <- expression.left.evaluate(symbolTable, valueStore)
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
      } yield leftAnswer.and(rightAnswer)
    }
  }

  implicit class NegateOps(expression: Negate) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      for {
        rightAnswer <- expression.right.evaluate(symbolTable, valueStore)
      } yield rightAnswer.negate
    }
  }

  implicit class ReferenceOps(expression: Reference) {
    def evaluate(symbolTable: SymbolTable, valueStore: ValueStore): Option[Answer] = {
      symbolTable
        .get(expression.questionId)
        .flatMap(_.expression)
        .orElse(valueStore.get(expression.questionId))
        .flatMap(_.evaluate(symbolTable, valueStore))
    }
  }
}

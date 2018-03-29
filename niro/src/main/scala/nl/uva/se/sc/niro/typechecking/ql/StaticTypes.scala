package nl.uva.se.sc.niro.typechecking.ql

import cats.implicits._
import nl.uva.se.sc.niro.PrettyPrinter._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import nl.uva.se.sc.niro.model.ql.{ IntegerType, NumericType, _ }
import org.apache.logging.log4j.scala.Logging

object StaticTypes extends Logging {
  def check(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] = {
    logger.info("Phase 3 - Checking operands of invalid type to operators ...")

    val conditionals = Statement.collectAllConditionals(qLForm.statements)
    val predicates = conditionals.map(_.predicate)

    val expressions: Iterable[Expression] = qLForm.symbolTable.values.flatMap(_.expression)

    (expressions ++ predicates)
      .map(expression => expression.typeOf(qLForm.symbolTable))
      .map(either => either.toValidatedNel)
      .toList
      .sequenceU_
      .as(AnswerType)
      .toEither
      .left
      .map(_.toList)
      .right
      .map(_ => qLForm)
  }

  private def validateNumericExpression(
      expression: BinaryExpression,
      symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      leftType <- expression.left.typeOf(symbolTable)
      rightType <- expression.right.typeOf(symbolTable)
      result <- if (leftType.isCompatibleWith(rightType) && NumericType.isCompatibleWith(leftType)) NumericType.asRight
      else TypeCheckError("TypeCheckError", s"Not a valid expression: ${expression.prettyPrint}").asLeft
    } yield result
  }

  private def validateComparisonExpression(
      expression: BinaryExpression,
      symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      leftType <- expression.left.typeOf(symbolTable)
      rightType <- expression.right.typeOf(symbolTable)
      result <- if (leftType.isCompatibleWith(rightType)) BooleanType.asRight
      else TypeCheckError("TypeCheckError", s"Not a valid expression: ${expression.prettyPrint}").asLeft
    } yield result
  }

  private def validateBooleanExpression(
      expression: BinaryExpression,
      symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      leftType <- expression.left.typeOf(symbolTable)
      rightType <- expression.right.typeOf(symbolTable)
      result <- if (BooleanType.isCompatibleWith(leftType) && BooleanType.isCompatibleWith(rightType))
        BooleanType.asRight
      else TypeCheckError("TypeCheckError", s"Not a valid expression: ${expression.prettyPrint}").asLeft
    } yield result
  }

  implicit class ExpressionCanTypeCheck(expression: Expression) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = expression match {
      case r: Reference        => r.typeOf(symbolTable)
      case a: Addition         => a.typeOf(symbolTable)
      case s: Subtract         => s.typeOf(symbolTable)
      case m: Multiply         => m.typeOf(symbolTable)
      case d: Divide           => d.typeOf(symbolTable)
      case m: Minus            => m.typeOf(symbolTable)
      case l: LessThan         => l.typeOf(symbolTable)
      case l: LessThanEqual    => l.typeOf(symbolTable)
      case g: GreaterThenEqual => g.typeOf(symbolTable)
      case g: GreaterThen      => g.typeOf(symbolTable)
      case n: NotEqual         => n.typeOf(symbolTable)
      case e: Equal            => e.typeOf(symbolTable)
      case o: Or               => o.typeOf(symbolTable)
      case a: And              => a.typeOf(symbolTable)
      case n: Negate           => n.typeOf(symbolTable)
      case i: IntegerAnswer    => i.typeOf.asRight
      case d: DecimalAnswer    => d.typeOf.asRight
      case m: MoneyAnswer      => m.typeOf.asRight
      case b: BooleanAnswer    => b.typeOf.asRight
      case s: StringAnswer     => s.typeOf.asRight
      case d: DateAnswer       => d.typeOf.asRight
    }
  }

  implicit class IntegerCanTypeCheck(expression: IntegerAnswer) {
    def typeOf: AnswerType = {
      IntegerType
    }
  }

  implicit class DecimalAnswerCanTypeCheck(expression: DecimalAnswer) {
    def typeOf: AnswerType = {
      DecimalType
    }
  }

  implicit class MoneyAnswerCanTypeCheck(expression: MoneyAnswer) {
    def typeOf: AnswerType = {
      MoneyType
    }
  }

  implicit class BooleanAnswerCanTypeCheck(expression: BooleanAnswer) {
    def typeOf: AnswerType = {
      BooleanType
    }
  }

  implicit class StringAnswerCanTypeCheck(expression: StringAnswer) {
    def typeOf: AnswerType = {
      StringType
    }
  }

  implicit class DateAnswerCanTypeCheck(expression: DateAnswer) {
    def typeOf: AnswerType = {
      DateType
    }
  }

  implicit class ReferenceCanTypeCheck(expression: Reference) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      symbolTable(expression.questionId).answerType.asRight
    }
  }

  implicit class AdditionCanTypeCheck(expression: Addition) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateNumericExpression(expression, symbolTable)
    }
  }

  implicit class SubtractCanTypeCheck(expression: Subtract) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateNumericExpression(expression, symbolTable)
    }
  }

  implicit class MultiplyCanTypeCheck(expression: Multiply) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateNumericExpression(expression, symbolTable)
    }
  }

  implicit class DivideCanTypeCheck(expression: Divide) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateNumericExpression(expression, symbolTable)
    }
  }

  implicit class MinusCanTypeCheck(expression: Minus) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      for {
        rightType <- expression.right.typeOf(symbolTable)
        _ <- if (NumericType.isCompatibleWith(rightType)) NumericType.asRight
        else TypeCheckError("TypeCheckError", s"Not a valid expression: ${expression.prettyPrint}").asLeft
      } yield NumericType
    }
  }

  implicit class LessThanCanTypeCheck(expression: LessThan) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateComparisonExpression(expression, symbolTable)
    }
  }

  implicit class LessThanEqualCanTypeCheck(expression: LessThanEqual) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateComparisonExpression(expression, symbolTable)
    }
  }

  implicit class GreaterThenEqualCanTypeCheck(expression: GreaterThenEqual) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateComparisonExpression(expression, symbolTable)
    }
  }

  implicit class GreaterThenCanTypeCheck(expression: GreaterThen) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateComparisonExpression(expression, symbolTable)
    }
  }

  implicit class NotEqualCanTypeCheck(expression: NotEqual) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateComparisonExpression(expression, symbolTable)
    }
  }

  implicit class EqualCanTypeCheck(expression: Equal) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateComparisonExpression(expression, symbolTable)
    }
  }

  implicit class OrCanTypeCheck(expression: Or) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateBooleanExpression(expression, symbolTable)
    }
  }

  implicit class AndCanTypeCheck(expression: And) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      validateBooleanExpression(expression, symbolTable)
    }
  }

  implicit class NegateCanTypeCheck(expression: Negate) {
    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
      for {
        rightType <- expression.right.typeOf(symbolTable)
        result <- if (BooleanType.isCompatibleWith(rightType)) BooleanType.asRight
        else TypeCheckError("TypeCheckError", s"Not a valid expression: ${expression.prettyPrint}").asLeft
      } yield result
    }
  }
}
package nl.uva.se.sc.niro.ql.parser.typecheck

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers._
import nl.uva.se.sc.niro.ql.model.ast.expressions.{ BinaryExpression, Reference, _ }
import nl.uva.se.sc.niro.ql.model.ast.{
  AnswerType,
  BooleanType,
  DateType,
  DecimalType,
  IntegerType,
  MoneyType,
  QLForm,
  StringType,
  Symbol
}
import nl.uva.se.sc.niro.util.PrettyPrinter._
import org.apache.logging.log4j.scala.Logging

object StaticTypes extends Logging {
  def check(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] = {
    logger.info("Phase 3 - Checking types ...")

    val checkedExpressions: List[Either[TypeCheckError, AnswerType]] = qLForm.symbolTable
      .filter(_._2.expression.isDefined)
      .map(entry => checkSymbolTableEntry(entry, qLForm.symbolTable))
      .toList

    checkedExpressions
      .map(either => either.toValidatedNel)
      .sequenceU_
      .as(AnswerType)
      .toEither
      .left
      .map(_.toList)
      .right
      .map(_ => qLForm)
  }

  def checkSymbolTableEntry(entry: (String, Symbol), symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      inferredAnswerType <- entry._2.expression.get.typeOf(symbolTable)
      result <- if (inferredAnswerType == entry._2.answerType) inferredAnswerType.asRight
      else
        TypeCheckError(message =
          s"Expression of type $inferredAnswerType does not conform to expected type ${entry._2.answerType}").asLeft
    } yield result
  }

  private def validateNumericExpression(
      expression: BinaryExpression,
      symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      leftType <- expression.left.typeOf(symbolTable)
      rightType <- expression.right.typeOf(symbolTable)
      resultingType = leftType.getWidest(rightType)
      result <- if (leftType.isNumber && rightType.isNumber && resultingType.isDefined) resultingType.get.asRight
      else TypeCheckError(message = s"Not a valid expression: ${expression.prettyPrint}").asLeft
    } yield result
  }

  private def validateComparisonExpression(
      expression: BinaryExpression,
      symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      leftType <- expression.left.typeOf(symbolTable)
      rightType <- expression.right.typeOf(symbolTable)
      resultingType = leftType.getWidest(rightType)
      result <- if (resultingType.isDefined) BooleanType.asRight
      else TypeCheckError(message = s"Not a valid expression: ${expression.prettyPrint}").asLeft
    } yield result
  }

  private def validateBooleanExpression(
      expression: BinaryExpression,
      symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      leftType <- expression.left.typeOf(symbolTable)
      rightType <- expression.right.typeOf(symbolTable)
      resultingType = leftType.getWidest(rightType)
      result <- if (resultingType.isDefined) BooleanType.asRight
      else TypeCheckError(message = s"Not a valid expression: ${expression.prettyPrint}").asLeft
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

  implicit class AnswerTypesCanTypeCheck(answerType: AnswerType) {
    def isNumber: Boolean = answerType match {
      case IntegerType => true
      case DecimalType => true
      case MoneyType   => true
      case BooleanType => false
      case StringType  => false
      case DateType    => false
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
        resultingType = IntegerType.getWidest(rightType)
        result <- if (rightType.isNumber && resultingType.isDefined) resultingType.get.asRight
        else TypeCheckError(message = s"Not a valid expression: ${expression.prettyPrint}").asLeft
      } yield result
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
        resultingType = BooleanType.getWidest(rightType)
        result <- if (resultingType.isDefined) BooleanType.asRight
        else TypeCheckError(message = s"Not a valid expression: ${expression.prettyPrint}").asLeft
      } yield result
    }
  }
}

package nl.uva.se.sc.niro.typechecking.ql

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import nl.uva.se.sc.niro.model.ql.{ IntegerType, _ }
import org.apache.logging.log4j.scala.Logging
import nl.uva.se.sc.niro.model.ql.evaluation.ExpressionEvaluator._
object StaticTypeChecker extends Logging {
  def checkOperandsOfInvalidTypeToOperators(qLForm: QLForm): Either[List[TypeCheckError], QLForm] = {
    logger.info("Phase 3 - Checking operands of invalid type to operators ...")

    val conditionals = Statement.collectAllConditionals(qLForm.statements)
    val predicates = conditionals.map(_.predicate)

    val expressions: Iterable[Expression] = qLForm.symbolTable.values.flatMap(_.expression)

//    (expressions ++ predicates)
//      .map(expression => expression.evaluate(qLForm.symbolTable, Map.empty))
//      .map(either => either.toValidatedNel)
//      .toList
//      .sequenceU_
//      .as(AnswerType)
//      .toEither
//      .left
//      .map(_.toList)
//      .right
//      .map(_ => qLForm)
    Right(qLForm)
  }

  // TODO implement type widener
  private def checkLeftRight(leftType: AnswerType, rightType: AnswerType): Either[TypeCheckError, AnswerType] = {
    if (leftType != rightType)
      TypeCheckError(message = s"Operands of invalid type: $leftType, $rightType").asLeft
    else
      rightType.asRight
  }

//  implicit class ExpressionCanTypeCheck(expression: Expression) {
//    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = expression match {
//      case r: Reference        => r.typeOf(symbolTable)
//      case a: Addition         => a.typeOf(symbolTable)
//      case s: Subtract         => s.typeOf(symbolTable)
//      case m: Multiply         => m.typeOf(symbolTable)
//      case d: Divide           => d.typeOf(symbolTable)
//      case m: Minus            => m.typeOf(symbolTable)
//      case l: LessThan         => l.typeOf(symbolTable)
//      case l: LessThanEqual    => l.typeOf(symbolTable)
//      case g: GreaterThenEqual => g.typeOf(symbolTable)
//      case g: GreaterThen      => g.typeOf(symbolTable)
//      case n: NotEqual         => n.typeOf(symbolTable)
//      case e: Equal            => e.typeOf(symbolTable)
//      case o: Or               => o.typeOf(symbolTable)
//      case a: And              => a.typeOf(symbolTable)
//      case n: Negate           => n.typeOf(symbolTable)
//      case i: IntegerAnswer    => i.typeOf(symbolTable)
//      case d: DecimalAnswer    => d.typeOf(symbolTable)
//      case m: MoneyAnswer      => m.typeOf(symbolTable)
//      case b: BooleanAnswer    => b.typeOf(symbolTable)
//      case s: StringAnswer     => s.typeOf(symbolTable)
//      case d: DateAnswer       => d.typeOf(symbolTable)
//    }
//  }

  implicit class AnswerCanTypeCheck(answer: Answer) {
    def typeOf = answer match {
      case i: IntegerAnswer => i.typeOf
      case d: DecimalAnswer => d.typeOf
      case m: MoneyAnswer   => m.typeOf
      case b: BooleanAnswer => b.typeOf
      case s: StringAnswer  => s.typeOf
      case d: DateAnswer    => d.typeOf
    }
  }

  implicit class IntegerCanTypeCheck(integerAnswer: IntegerAnswer) {
    def typeOf: AnswerType = {
      IntegerType
    }
  }

  implicit class DecimalAnswerCanTypeCheck(answer: DecimalAnswer) {
    def typeOf(symbolTable: SymbolTable): AnswerType = {
      DecimalType
    }
  }

  implicit class MoneyAnswerCanTypeCheck(answer: MoneyAnswer) {
    def typeOf(symbolTable: SymbolTable): AnswerType = {
      MoneyType
    }
  }

  implicit class BooleanAnswerCanTypeCheck(answer: BooleanAnswer) {
    def typeOf(symbolTable: SymbolTable): AnswerType = {
      BooleanType
    }
  }

  implicit class StringAnswerCanTypeCheck(answer: StringAnswer) {
    def typeOf(symbolTable: SymbolTable): AnswerType = {
      StringType
    }
  }

  implicit class DateAnswerCanTypeCheck(answer: DateAnswer) {
    def typeOf(symbolTable: SymbolTable): AnswerType = {
      DateType
    }
  }
//  implicit class ReferenceCanTypeCheck(operation: Reference) {
//    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      symbolTable(operation.questionId).answerType.asRight
//    }
//  }
//
//  implicit class AdditionCanTypeCheck(operation: Addition) {
//    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class SubtractCanTypeCheck(operation: Subtract) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class MultiplyCanTypeCheck(operation: Multiply) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class DivideCanTypeCheck(operation: Divide) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class MinusCanTypeCheck(operation: Minus) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class LessThanCanTypeCheck(operation: LessThan) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class LessThanEqualCanTypeCheck(operation: LessThanEqual) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class GreaterThenEqualCanTypeCheck(operation: GreaterThenEqual) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class GreaterThenCanTypeCheck(operation: GreaterThen) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class NotEqualCanTypeCheck(operation: NotEqual) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class EqualCanTypeCheck(operation: Equal) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class OrCanTypeCheck(operation: Or) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class AndCanTypeCheck(operation: And) {
//    def typeOf(symbol: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
//
//  implicit class NegateCanTypeCheck(operation: Negate) {
//    def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
//      ???
//    }
//  }
}

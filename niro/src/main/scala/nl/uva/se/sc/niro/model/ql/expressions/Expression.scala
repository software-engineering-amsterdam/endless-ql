package nl.uva.se.sc.niro.model.ql.expressions

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.{ AnswerType, Operator }

abstract class Expression {
  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType]
}

final case class Reference(questionId: String) extends Expression {
  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = symbolTable(questionId).answerType.asRight
}

final case class BinaryOperation(operator: Operator, left: Expression, right: Expression) extends Expression {
  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = {
    for {
      leftType <- left.typeOf(symbolTable)
      leftTypeAfterOperation <- leftType.typeOf(operator)
      rightType <- right.typeOf(symbolTable)
      _ <- rightType.typeOf(operator)
      _ <- leftType.isCompatibleWith(rightType)
    } yield leftTypeAfterOperation
  }
}

final case class UnaryOperation(operator: Operator, left: Expression) extends Expression {
  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] =
    left.typeOf(symbolTable).flatMap((answerType: AnswerType) => answerType.typeOf(operator))
}

object Expression {
  def collectAllReferences(expression: Expression): Seq[Reference] = expression match {
    case r: Reference                       => Seq(r)
    case UnaryOperation(_, rightExpression) => collectAllReferences(rightExpression)
    case BinaryOperation(_, leftExpression, rightExpression) =>
      collectAllReferences(leftExpression) ++ collectAllReferences(rightExpression)
    case _ => Seq.empty
  }
}

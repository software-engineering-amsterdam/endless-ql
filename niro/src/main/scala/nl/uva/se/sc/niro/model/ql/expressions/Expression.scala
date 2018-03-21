package nl.uva.se.sc.niro.model.ql.expressions

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.Operators._

abstract class Expression {
  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType]
}

final case class Reference(questionId: String) extends Expression {
  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] = symbolTable(questionId).answerType.asRight
}

abstract class BinaryOperation extends Expression {

  val left: Expression
  val right: Expression
  val operator: Operator

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
abstract class UnaryOperation extends Expression {
  val left: Expression
  val operator: Operator
  def typeOf(symbolTable: SymbolTable): Either[TypeCheckError, AnswerType] =
    left.typeOf(symbolTable).flatMap((answerType: AnswerType) => answerType.typeOf(operator))
}

final case class Addition(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Add
}
final case class Subtract(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Sub

}
final case class Multiply(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Mul

}
final case class Divide(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Div
}
final case class Minus(left: Expression) extends UnaryOperation {
  val operator: Operator = Sub
}
final case class LessThan(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Lt
}
final case class LessThanEqual(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Lte
}
final case class GreaterThenEqual(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Gte
}
final case class GreaterThen(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Gt
}
final case class NotEqual(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Ne
}
final case class Equal(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = Eq
}
final case class Or(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = nl.uva.se.sc.niro.model.ql.Operators.Or
}
final case class And(left: Expression, right: Expression) extends BinaryOperation {
  val operator: Operator = nl.uva.se.sc.niro.model.ql.Operators.And
}
final case class Negate(left: Expression) extends UnaryOperation {
  val operator: Operator = nl.uva.se.sc.niro.model.ql.Operators.Neg
}

object Expression {
  def collectAllReferences(expression: Expression): Seq[Reference] = expression match {
    case r: Reference       => Seq(r)
    case u: UnaryOperation  => collectAllReferences(u.left)
    case b: BinaryOperation => collectAllReferences(b.left) ++ collectAllReferences(b.right)
    case _                  => Seq.empty
  }
}

object UnaryOperation {
  def apply(operator: Operator, left: Expression): UnaryOperation = operator match {
    case Operators.Sub => Minus(left)
    case Operators.Neg => Negate(left)
    case _ => throw new IllegalArgumentException(s"Unknown operator $operator")
  }
}

object BinaryOperation {
  def apply(operator: Operator, left: Expression, right: Expression): BinaryOperation = operator match {
    case Operators.Lt  => LessThan(left, right)
    case Operators.Lte => LessThanEqual(left, right)
    case Operators.Eq  => Equal(left, right)
    case Operators.Ne  => NotEqual(left, right)
    case Operators.Gte => GreaterThenEqual(left, right)
    case Operators.Gt  => GreaterThen(left, right)
    case Operators.Sub => Subtract(left, right)
    case Operators.Add => Addition(left, right)
    case Operators.Div => Divide(left, right)
    case Operators.Mul => Multiply(left, right)
    case Operators.Or  => Or(left, right)
    case Operators.And => And(left, right)
    case _ => throw new IllegalArgumentException(s"Unknown operator $operator")
  }
}

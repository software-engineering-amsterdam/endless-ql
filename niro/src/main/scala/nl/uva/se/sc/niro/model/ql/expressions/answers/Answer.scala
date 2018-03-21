package nl.uva.se.sc.niro.model.ql.expressions.answers

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.ql.Operators.Operator
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions.Expression

abstract class Answer extends Expression {

  type T

  val value: T

  def applyUnaryOperator(operator: Operator): Answer

  def applyBinaryOperator(operator: Operator, right: Answer): Answer

  def isTrue: Boolean = false

  def typeOf(symbolTable: SymbolTable): Either[Errors.TypeCheckError, AnswerType] = typeOf.asRight

  def typeOf: AnswerType
}

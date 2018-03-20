package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operator
import nl.uva.se.sc.niro.model.ql.expressions.Expression

abstract class Answer extends Expression {

  type T

  val value: T

  def applyUnaryOperator(operator: Operator): Answer

  def applyBinaryOperator(operator: Operator, right: Answer): Answer

  def isTrue: Boolean = false
}

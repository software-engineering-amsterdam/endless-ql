package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }

final case class DecAnswer(possibleValue: Option[Double]) extends Answer {

  def applyUnaryOperator(unaryOperator: UnaryOperator): Answer = ???
  def applyBinaryOperator(binaryOperator: BinaryOperator, other: Answer): Answer = ???
}

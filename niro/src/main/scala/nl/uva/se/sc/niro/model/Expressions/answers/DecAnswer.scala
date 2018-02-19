package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.Operators.{ BinaryOperator, UnaryOperator }

case class DecAnswer(possibleValue: Option[String]) extends Answer {
  def apply(unaryOperator: UnaryOperator): Answer = ???
  def apply(binaryOperator: BinaryOperator, other: Answer): Answer = ???
}

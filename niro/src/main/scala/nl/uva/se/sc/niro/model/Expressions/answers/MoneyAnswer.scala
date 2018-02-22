package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }

case class MoneyAnswer(possibleValue: Option[String]) extends Answer {
  def apply(unaryOperator: UnaryOperator): Answer = ???
  def apply(binaryOperator: BinaryOperator, other: Answer): Answer = ???
}

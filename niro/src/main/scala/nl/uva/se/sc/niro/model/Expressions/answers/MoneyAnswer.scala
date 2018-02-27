package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }

final case class MoneyAnswer(possibleValue: Option[String]) extends Answer {

  def applyUnaryOperator(unaryOperator: UnaryOperator): Answer = ???
  def applyBinaryOperator(binaryOperator: BinaryOperator, other: Answer): Answer = ???
}

object MoneyAnswer {
  def apply() = new MoneyAnswer(None)
  def apply(value: String) = new MoneyAnswer(Some(value))
}

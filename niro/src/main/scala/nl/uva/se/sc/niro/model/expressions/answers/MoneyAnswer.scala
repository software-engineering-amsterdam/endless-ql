package nl.uva.se.sc.niro.model.expressions.answers

import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }

final case class MoneyAnswer(possibleValue: Option[BigDecimal]) extends Answer {

  type T = BigDecimal

  def applyUnaryOperator(unaryOperator: UnaryOperator): Answer = ???
  def applyBinaryOperator(binaryOperator: BinaryOperator, other: Answer): Answer = ???
}

object MoneyAnswer {
  def apply() = new MoneyAnswer(None)
  def apply(value: BigDecimal) = new MoneyAnswer(Some(value))
}

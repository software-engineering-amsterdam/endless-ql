package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model.{ BinaryOperator, UnaryOperator }

final case class DecAnswer(possibleValue: Option[Double]) extends Answer {
  def applyUnaryOperator(unaryOperator: UnaryOperator): Answer = ???
  def applyBinaryOperator(binaryOperator: BinaryOperator, other: Answer): Answer = ???

  private def combine[R](other: DecAnswer)(f: (Double, Double) => R): Option[R] = for {
    thisValue <- possibleValue
    thatValue <- other.possibleValue
  } yield f(thisValue, thatValue)
}

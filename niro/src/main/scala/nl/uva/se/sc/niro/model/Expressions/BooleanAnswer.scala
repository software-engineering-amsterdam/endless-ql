package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.{ Comparisons, Logicals }
import nl.uva.se.sc.niro.model.Operators._

case class BooleanAnswer(possibleValue: Option[Boolean]) extends Answer with Comparisons[BooleanAnswer] with Logicals[BooleanAnswer] {

  def lt(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ < value)))
  def lTe(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ <= value)))
  def gTe(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ >= value)))
  def gt(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ > value)))
  def ne(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ != value)))
  def eq(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ == value)))


  def and(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ && value)))
  def or(other: BooleanAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ || value)))

  def neg: BooleanAnswer = BooleanAnswer(possibleValue.map(!_))

  def apply(operator: Operator, right: Answer): Answer = right match {
    case rhs: BooleanAnswer => operator match {
      case Lt => lt(rhs)
      case LTe => lTe(rhs)
      case GTe => gTe(rhs)
      case Gt => gt(rhs)
      case Ne => ne(rhs)
      case Eq => eq(rhs)
      case _ => throw new UnsupportedOperationException(s"Unsupported $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation on different types")
  }

  def apply(operator: Operator): Answer = operator match {
    case Neg => neg
    case _ => throw new UnsupportedOperationException(s"Cant perform $operator on BooleanAnswer")
  }
}
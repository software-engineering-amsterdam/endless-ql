package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Comparisons
import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.Operators._

case class StringAnswer(possibleValue: Option[String]) extends Answer with Comparisons[StringAnswer] {
  def lt(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ < value)))
  def lTe(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ <= value)))
  def gTe(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ >= value)))
  def gt(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ > value)))
  def ne(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ != value)))
  def eq(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ == value)))

  def apply(operator: Operator, right: Answer): Answer = right match {
    case rhs: StringAnswer => operator match {
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

  def apply(operator: Operator): Answer = throw new UnsupportedOperationException("Unary operators are not supported on StringAnswer")
}

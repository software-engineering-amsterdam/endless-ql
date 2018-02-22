package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model._

case class StringAnswer(possibleValue: Option[String]) extends Answer {
  def lt(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(value < _)))
  def lTe(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(value <= _)))
  def gTe(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(value >= _)))
  def gt(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(value > _)))
  def ne(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(value != _)))
  def eq(other: StringAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(value == _)))

  def apply(operator: BinaryOperator, right: Answer): Answer = right match {
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

  def apply(operator: UnaryOperator): Answer = throw new UnsupportedOperationException("Unary operators are not supported on StringAnswer")
}

package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model.{ Arithmetics, Comparisons }
import nl.uva.se.sc.niro.model.Operators._

case class IntAnswer(possibleValue: Option[Int]) extends Answer with Arithmetics[IntAnswer] with Comparisons[IntAnswer] {

  def add(other: IntAnswer): IntAnswer = IntAnswer(possibleValue.flatMap(value => other.possibleValue.map(otherValue => otherValue + value)))
  def sub(other: IntAnswer): IntAnswer = IntAnswer(possibleValue.flatMap(value => other.possibleValue.map(otherValue => otherValue - value)))
  def mul(other: IntAnswer): IntAnswer = IntAnswer(possibleValue.flatMap(value => other.possibleValue.map(otherValue => otherValue * value)))
  def div(other: IntAnswer): IntAnswer = IntAnswer(possibleValue.flatMap(value => other.possibleValue.map(otherValue => otherValue / value)))

  def lt(other: IntAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ < value)))
  def lTe(other: IntAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ <= value)))
  def gTe(other: IntAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ >= value)))
  def gt(other: IntAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ > value)))
  def ne(other: IntAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ != value)))
  def eq(other: IntAnswer): BooleanAnswer = BooleanAnswer(possibleValue.flatMap(value => other.possibleValue.map(_ == value)))

  def min: IntAnswer = IntAnswer(possibleValue.map(-_))

  def apply(operator: Operator, other: Answer): Answer = (this, other) match {
    case (lhs: IntAnswer, rhs: IntAnswer) => operator match {
      case Add => add(rhs)
      case Sub => sub(rhs)
      case Mul => mul(rhs)
      case Div => div(rhs)
      case Lt => lt(rhs)
      case LTe => lTe(rhs)
      case GTe => gTe(rhs)
      case Gt => gt(rhs)
      case Ne => ne(rhs)
      case Eq => eq(rhs)
      case _ => throw new UnsupportedOperationException(s"Unsupported $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation on different types. Type ${this.getClass.getSimpleName} and ${other.getClass.getSimpleName}")
  }

  def apply(operator: Operator): Answer = operator match {
    case Min => min
  }
}

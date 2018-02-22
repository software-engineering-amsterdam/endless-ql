package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model._

case class StringAnswer(possibleValue: Option[String]) extends Answer {

  def apply(operator: BinaryOperator, other: Answer): Answer = other match {
    case otherStringAnswer: StringAnswer => operator match {
      case Lt => BooleanAnswer(combine[Boolean](otherStringAnswer)(_ < _))
      case LTe => BooleanAnswer(combine[Boolean](otherStringAnswer)(_ <= _))
      case GTe => BooleanAnswer(combine[Boolean](otherStringAnswer)(_ >= _))
      case Gt => BooleanAnswer(combine[Boolean](otherStringAnswer)(_ > _))
      case Ne => BooleanAnswer(combine[Boolean](otherStringAnswer)(_ != _))
      case Eq => BooleanAnswer(combine[Boolean](otherStringAnswer)(_ == _))
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $other")
  }

  def combine[R](other: StringAnswer)(f: (String, String) => R): Option[R] = for {
    thisValue <- possibleValue
    thatValue <- other.possibleValue
  } yield f(thisValue, thatValue)

  def apply(operator: UnaryOperator): Answer = throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
}

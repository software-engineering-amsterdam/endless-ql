package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model.Expressions.Orderings.StringAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model._

final case class StringAnswer(possibleValue: Option[String]) extends Answer {

  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: StringAnswer => operator match {
      case Lt => this < that
      case LTe => this <= that
      case GTe => this >= that
      case Gt => this > that
      case Ne => BooleanAnswer(combine[Boolean](that)(_ != _))
      case Eq => BooleanAnswer(combine[Boolean](that)(_ == _))
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: UnaryOperator): Answer = throw new IllegalArgumentException(s"Can't perform operation: $operator $this")

  private def combine[R](other: StringAnswer)(f: (String, String) => R): Option[R] = for {
    thisValue <- possibleValue
    thatValue <- other.possibleValue
  } yield f(thisValue, thatValue)
}

object StringAnswer {
  def apply() = new StringAnswer(None)
  def apply(value: String) = new StringAnswer(Some(value))
}
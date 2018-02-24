package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.model.Expressions.Orderings.BooleanAnswerCanDoOrderings._

final case class BooleanAnswer(possibleValue: Option[Boolean]) extends Answer {

  override def isTrue: Boolean = possibleValue.getOrElse(false)

  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: BooleanAnswer => operator match {
      case Lt => this < that
      case LTe => this <= that
      case GTe => this >= that
      case Gt => this > that
      case Ne => BooleanAnswer(combine[Boolean](that)(_ != _))
      case Eq => BooleanAnswer(combine[Boolean](that)(_ == _))
      case And => BooleanAnswer(combine[Boolean](that)(_ && _))
      case Or => BooleanAnswer(combine[Boolean](that)(_ || _))
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: UnaryOperator): Answer = operator match {
    case Neg => BooleanAnswer(possibleValue.map(!_))
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }

  private def combine[R](other: BooleanAnswer)(f: (Boolean, Boolean) => R): Option[R] = for {
    thisValue <- possibleValue
    thatValue <- other.possibleValue
  } yield f(thisValue, thatValue)
}

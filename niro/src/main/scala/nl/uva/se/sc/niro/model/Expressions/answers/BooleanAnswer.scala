package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model._

final case class BooleanAnswer(possibleValue: Option[Boolean]) extends Answer {

  def applyBinaryOperator(operator: BinaryOperator, other: Answer): Answer = other match {
    case otherBooleanAnswer: BooleanAnswer => operator match {
      case Lt => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ < _))
      case LTe => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ <= _))
      case GTe => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ >= _))
      case Gt => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ > _))
      case Ne => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ != _))
      case Eq => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ == _))
      case And => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ && _))
      case Or => BooleanAnswer(combine[Boolean](otherBooleanAnswer)(_ || _))
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $other")
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
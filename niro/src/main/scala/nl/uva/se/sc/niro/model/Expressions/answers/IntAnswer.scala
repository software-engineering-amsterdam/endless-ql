package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model._

final case class IntAnswer(possibleValue: Option[Int]) extends Answer {

  def applyBinaryOperator(operator: BinaryOperator, other: Answer): Answer = other match {
    case otherIntAnswer: IntAnswer => operator match {
      case Add => IntAnswer(combine[Int](otherIntAnswer)(_ + _))
      case Sub => IntAnswer(combine[Int](otherIntAnswer)(_ - _))
      case Mul => IntAnswer(combine[Int](otherIntAnswer)(_ * _))
      case Div => IntAnswer(combine[Int](otherIntAnswer)(_ / _))
      case Lt => BooleanAnswer(combine[Boolean](otherIntAnswer)(_ < _))
      case LTe => BooleanAnswer(combine[Boolean](otherIntAnswer)(_ <= _))
      case GTe => BooleanAnswer(combine[Boolean](otherIntAnswer)(_ >= _))
      case Gt => BooleanAnswer(combine[Boolean](otherIntAnswer)(_ > _))
      case Ne => BooleanAnswer(combine[Boolean](otherIntAnswer)(_ != _))
      case Eq => BooleanAnswer(combine[Boolean](otherIntAnswer)(_ == _))
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $other")
  }

  private def combine[R](other: IntAnswer)(f: (Int, Int) => R): Option[R] = for {
    thisValue <- possibleValue
    thatValue <- other.possibleValue
  } yield f(thisValue, thatValue)

  def applyUnaryOperator(operator: UnaryOperator): Answer = operator match {
    case Min => IntAnswer(possibleValue.map(-_))
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

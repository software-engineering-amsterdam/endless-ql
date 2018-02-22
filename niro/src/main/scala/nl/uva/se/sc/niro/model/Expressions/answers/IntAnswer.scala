package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Expression.Answer
import nl.uva.se.sc.niro.model._

case class IntAnswer(possibleValue: Option[Int]) extends Answer {

  def apply(operator: BinaryOperator, other: Answer): Answer = other match {
    case (other: IntAnswer) => operator match {
      case Add => IntAnswer(combine[Int](other)(_ + _))
      case Sub => IntAnswer(combine[Int](other)(_ - _))
      case Mul => IntAnswer(combine[Int](other)(_ * _))
      case Div => IntAnswer(combine[Int](other)(_ / _))
      case Lt => BooleanAnswer(combine[Boolean](other)(_ < _))
      case LTe => BooleanAnswer(combine[Boolean](other)(_ <= _))
      case GTe => BooleanAnswer(combine[Boolean](other)(_ >= _))
      case Gt => BooleanAnswer(combine[Boolean](other)(_ > _))
      case Ne => BooleanAnswer(combine[Boolean](other)(_ != _))
      case Eq => BooleanAnswer(combine[Boolean](other)(_ == _))
      case _ => throw new UnsupportedOperationException(s"Unsupported $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation on type ${other.getClass.getSimpleName}")
  }

  def combine[R](other: IntAnswer)(f: (Int, Int) => R): Option[R] = for {
    thisValue <- possibleValue
    thatValue <- other.possibleValue
  } yield f(thisValue, thatValue)

  def apply(operator: UnaryOperator): Answer = operator match {
    case Min => IntAnswer(possibleValue.map(-_))
  }
}

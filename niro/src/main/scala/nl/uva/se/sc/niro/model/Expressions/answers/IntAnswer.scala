package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.model.Expressions.BasicArithmetics.IntAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.Expressions.Orderings.IntAnswerCanDoOrderings._

final case class IntAnswer(possibleValue: Option[Int]) extends Answer {

  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: IntAnswer => operator match {
      case Add => plus(this, that)
      case Sub => minus(this, that)
      case Mul => times(this, that)
      case Div => div(this, that)
      case Lt => lt(this, that)
      case LTe => lte(this, that)
      case GTe => gte(this, that)
      case Gt => gt(this, that)
      case Ne => BooleanAnswer(combine[Boolean](that)(_ != _))
      case Eq => BooleanAnswer(combine[Boolean](that)(_ == _))
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
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

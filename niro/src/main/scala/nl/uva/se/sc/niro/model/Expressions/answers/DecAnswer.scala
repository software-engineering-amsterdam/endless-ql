package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model.Expressions.BasicArithmetics.DecAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.Expressions.Orderings.DecAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model._

final case class DecAnswer(possibleValue: Option[BigDecimal]) extends Answer {
  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: DecAnswer => operator match {
      case Add => this + that
      case Sub => this - that
      case Mul => this * that
      case Div => this / that
      case Lt => this < that
      case LTe => this <= that
      case GTe => this >= that
      case Gt => this > that
      case Ne => BooleanAnswer(this.possibleValue.flatMap(x => that.possibleValue.map(y => x != y)))
      case Eq => BooleanAnswer(this.possibleValue.flatMap(x => that.possibleValue.map(y => x == y)))
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: UnaryOperator): Answer = operator match {
    case Min => DecAnswer(possibleValue.map(-_))
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object DecAnswer {
  def apply() = new DecAnswer(None)
  def apply(value: BigDecimal) = new DecAnswer(Some(value))
}

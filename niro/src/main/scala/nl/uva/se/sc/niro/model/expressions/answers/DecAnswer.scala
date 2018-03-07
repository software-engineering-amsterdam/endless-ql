package nl.uva.se.sc.niro.model.expressions.answers

import nl.uva.se.sc.niro.model.expressions.Answer
import nl.uva.se.sc.niro.model.expressions.BasicArithmetics.DecAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.expressions.Orderings.DecAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model._

final case class DecAnswer(possibleValue: Option[BigDecimal]) extends Answer {

  type T = BigDecimal

  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: DecAnswer =>
      operator match {
        case Add => this + that
        case Sub => this - that
        case Mul => this * that
        case Div => this / that
        case Lt  => this < that
        case Lte => this <= that
        case Gte => this >= that
        case Gt  => this > that
        case Ne  => this !== that
        case Eq  => this === that
        case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: UnaryOperator): Answer = operator match {
    case Min => DecAnswer(possibleValue.map(-_))
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object DecAnswer {
  def apply() = new DecAnswer(None)
  def apply(value: BigDecimal) = new DecAnswer(Some(value))
  def apply(value: String): DecAnswer = if (value.isEmpty) DecAnswer() else DecAnswer(BigDecimal(value))
}

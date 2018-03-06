package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model.Expressions.BasicArithmetics.IntAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.Expressions.Orderings.IntAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model._

final case class IntAnswer(possibleValue: Option[Int]) extends Answer {

  type T = Int

  def toDecAnswer = DecAnswer(possibleValue.map(BigDecimal(_)))

  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: IntAnswer =>
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
    case that: DecAnswer => toDecAnswer.applyBinaryOperator(operator, that)
    case _               => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: UnaryOperator): Answer = operator match {
    case Min => -this
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object IntAnswer {
  def apply() = new IntAnswer(None)
  def apply(value: Int) = new IntAnswer(Some(value))
  def apply(value: String): IntAnswer = if (value.isEmpty) IntAnswer() else IntAnswer(Integer.parseInt(value))
}

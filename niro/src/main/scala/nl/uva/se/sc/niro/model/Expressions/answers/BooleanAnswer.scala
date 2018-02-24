package nl.uva.se.sc.niro.model.Expressions.answers

import nl.uva.se.sc.niro.model.Expressions.Answer
import nl.uva.se.sc.niro.model.Expressions.Logicals.BooleanAnswerCanDoLogicals._
import nl.uva.se.sc.niro.model.Expressions.Orderings.BooleanAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model._

final case class BooleanAnswer(possibleValue: Option[Boolean]) extends Answer {

  override def isTrue: Boolean = possibleValue.getOrElse(false)

  def applyBinaryOperator(operator: BinaryOperator, that: Answer): Answer = that match {
    case that: BooleanAnswer => operator match {
      case Lt => this < that
      case LTe => this <= that
      case GTe => this >= that
      case Gt => this > that
      case Ne => BooleanAnswer(this.possibleValue.flatMap(x => that.possibleValue.map(y => x != y)))
      case Eq => BooleanAnswer(this.possibleValue.flatMap(x => that.possibleValue.map(y => x == y)))
      case And => this && that
      case Or => this || that
      case _ => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
    }
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: UnaryOperator): Answer = operator match {
    case Neg => !this
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object BooleanAnswer {
  def apply() = new BooleanAnswer(None)
  def apply(value: Boolean) = new BooleanAnswer(Some(value))
}

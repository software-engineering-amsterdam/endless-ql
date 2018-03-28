package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, BooleanAnswer }

import scala.language.implicitConversions

// format: off
object Logicals {
  trait BooleanAnswerCanDoLogicals extends Logicals[BooleanAnswer] {
    def and(x: BooleanAnswer, y: Answer): BooleanAnswer = y match {
      case b: BooleanAnswer => BooleanAnswer(x.value && b.value)
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def or(x: BooleanAnswer, y: Answer): BooleanAnswer = y match {
      case b: BooleanAnswer => BooleanAnswer(x.value || b.value)
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def neg(x: BooleanAnswer): BooleanAnswer = BooleanAnswer(!x.value)
  }
  implicit object BooleanAnswerCanDoLogicals extends BooleanAnswerCanDoLogicals
}
// format: on

trait Logicals[SubType <: Answer] {
  def and(x: SubType, y: Answer): BooleanAnswer
  def or(x: SubType, y: Answer): BooleanAnswer
  def neg(x: SubType): BooleanAnswer

  class Ops(left: SubType) {
    def &&(right: Answer): BooleanAnswer = and(left, right)
    def ||(right: Answer): BooleanAnswer = or(left, right)
    def unary_!(): BooleanAnswer = neg(left)
  }

  implicit def mkLogicalsOp(left: SubType): Ops = new Ops(left)
}

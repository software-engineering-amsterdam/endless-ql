package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ Answer, BooleanAnswer }

import scala.language.implicitConversions

// format: off
object Logicals {
  trait BooleanAnswerCanDoLogicals extends Logicals[BooleanAnswer] {
    def and(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value && b.value) }
    def or(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value || b.value) }
    def neg(left: BooleanAnswer): BooleanAnswer = BooleanAnswer(!left.value)
  }
  implicit object BooleanAnswerCanDoLogicals extends BooleanAnswerCanDoLogicals
}
// format: on

trait Logicals[SubType <: Answer] {
  def and(left: SubType, right: Answer): BooleanAnswer
  def or(left: SubType, right: Answer): BooleanAnswer
  def neg(left: SubType): BooleanAnswer

  class Ops(left: SubType) {
    def &&(right: Answer): BooleanAnswer = and(left, right)
    def ||(right: Answer): BooleanAnswer = or(left, right)
    def unary_!(): BooleanAnswer = neg(left)
  }

  implicit def mkLogicalsOp(left: SubType): Ops = new Ops(left)
}

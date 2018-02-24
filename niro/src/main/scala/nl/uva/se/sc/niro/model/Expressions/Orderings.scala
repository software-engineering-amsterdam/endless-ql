package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer }

object Orderings {
  trait IntAnswerCanDoOrderings extends Orderings[IntAnswer] {
    import IntAnswerCanDoOrderings._
    def lt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ < _))
    def lte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ <= _))
    def gte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ >= _))
    def gt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ > _))
    def neq(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ != _))
    def equ(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ == _))
  }
  implicit object IntAnswerCanDoOrderings extends IntAnswerCanDoOrderings {
    private def combine(x: IntAnswer, y: IntAnswer)(f: (Int, Int) => Boolean): Option[Boolean] = for {
      thisValue <- x.possibleValue
      thatValue <- y.possibleValue
    } yield f(thisValue, thatValue)
  }
}

trait Orderings[SubType<:Answer] {
  def lt(x: SubType, y: SubType): BooleanAnswer
  def lte(x: SubType, y: SubType): BooleanAnswer
  def gte(x: SubType, y: SubType): BooleanAnswer
  def gt(x: SubType, y: SubType): BooleanAnswer
  def neq(x: SubType, y: SubType): BooleanAnswer
  def equ(x: SubType, y: SubType): BooleanAnswer

  class Ops(left: SubType) {
    def <(right: SubType): BooleanAnswer = lt(left, right)
    def <=(right: SubType): BooleanAnswer = lte(left, right)
    def >=(right: SubType): BooleanAnswer = gte(left, right)
    def >(right: SubType): BooleanAnswer = gt(left, right)
    def !=(right: SubType): BooleanAnswer = neq(left, right)
    def ==(right: SubType): BooleanAnswer = equ(left, right)
  }

  implicit def mkOrderingOps(lhs: SubType): Ops = new Ops(lhs)
}
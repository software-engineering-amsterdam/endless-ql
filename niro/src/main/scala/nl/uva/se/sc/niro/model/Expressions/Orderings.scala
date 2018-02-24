package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer, StringAnswer }

object Orderings {
  trait IntAnswerCanDoOrderings extends Orderings[IntAnswer] {
    import IntAnswerCanDoOrderings._
    def lt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ < _))
    def lte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ <= _))
    def gte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ >= _))
    def gt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ > _))
  }
  implicit object IntAnswerCanDoOrderings extends IntAnswerCanDoOrderings {
    private def combine(x: IntAnswer, y: IntAnswer)(f: (Int, Int) => Boolean): Option[Boolean] = for {
      thisValue <- x.possibleValue
      thatValue <- y.possibleValue
    } yield f(thisValue, thatValue)
  }

  trait BooleanAnswerCanDoOrderings extends Orderings[BooleanAnswer] {
    import BooleanAnswerCanDoOrderings._
    def lt(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ < _))
    def lte(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ <= _))
    def gte(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ >= _))
    def gt(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ > _))
  }
  implicit object BooleanAnswerCanDoOrderings extends BooleanAnswerCanDoOrderings {
    private def combine(x: BooleanAnswer, y: BooleanAnswer)(f: (Boolean, Boolean) => Boolean): Option[Boolean] = for {
      thisValue <- x.possibleValue
      thatValue <- y.possibleValue
    } yield f(thisValue, thatValue)
  }

  trait StringAnswerCanDoOrderings extends Orderings[StringAnswer] {
    import StringAnswerCanDoOrderings._
    def lt(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ < _))
    def lte(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ <= _))
    def gte(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ >= _))
    def gt(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ > _))
  }
  implicit object StringAnswerCanDoOrderings extends StringAnswerCanDoOrderings {
    private def combine(x: StringAnswer, y: StringAnswer)(f: (String, String) => Boolean): Option[Boolean] = for {
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

  class Ops(left: SubType) {
    def <(right: SubType): BooleanAnswer = lt(left, right)
    def <=(right: SubType): BooleanAnswer = lte(left, right)
    def >=(right: SubType): BooleanAnswer = gte(left, right)
    def >(right: SubType): BooleanAnswer = gt(left, right)
  }

  implicit def mkOrderingOps(lhs: SubType): Ops = new Ops(lhs)
}
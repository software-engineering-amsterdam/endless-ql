package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, DecAnswer, IntAnswer, StringAnswer }

import scala.language.implicitConversions

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

  trait DecAnswerCanDoOrderings extends Orderings[DecAnswer] {
    import DecAnswerCanDoOrderings._
    def lt(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ < _))
    def lte(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ <= _))
    def gte(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ >= _))
    def gt(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ > _))
    def neq(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ != _))
    def equ(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ == _))
  }
  implicit object DecAnswerCanDoOrderings extends DecAnswerCanDoOrderings {
    private def combine(x: DecAnswer, y: DecAnswer)(f: (BigDecimal, BigDecimal) => Boolean): Option[Boolean] = for {
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
    def neq(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ != _))
    def equ(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ == _))
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
    def neq(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ != _))
    def equ(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ == _))
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
  def neq(x: SubType, y: SubType): BooleanAnswer
  def equ(x: SubType, y: SubType): BooleanAnswer

  class Ops(left: SubType) {
    def <(right: SubType): BooleanAnswer = lt(left, right)
    def <=(right: SubType): BooleanAnswer = lte(left, right)
    def >=(right: SubType): BooleanAnswer = gte(left, right)
    def >(right: SubType): BooleanAnswer = gt(left, right)
    def !==(right: SubType): BooleanAnswer = neq(left, right)
    def ===(right: SubType): BooleanAnswer = equ(left, right)
  }

  implicit def mkOrderingOps(left: SubType): Ops = new Ops(left)
}

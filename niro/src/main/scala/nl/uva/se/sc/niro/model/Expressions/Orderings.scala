package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, DecAnswer, IntAnswer, StringAnswer }

import scala.language.implicitConversions

object Orderings {
  trait IntAnswerCanDoOrderings extends Orderings[IntAnswer] {
    def lt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object IntAnswerCanDoOrderings extends IntAnswerCanDoOrderings

  trait DecAnswerCanDoOrderings extends Orderings[DecAnswer] {
    def lt(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: DecAnswer, y: DecAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object DecAnswerCanDoOrderings extends DecAnswerCanDoOrderings

  trait BooleanAnswerCanDoOrderings extends Orderings[BooleanAnswer] {
    def lt(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object BooleanAnswerCanDoOrderings extends BooleanAnswerCanDoOrderings

  trait StringAnswerCanDoOrderings extends Orderings[StringAnswer] {
    def lt(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object StringAnswerCanDoOrderings extends StringAnswerCanDoOrderings
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

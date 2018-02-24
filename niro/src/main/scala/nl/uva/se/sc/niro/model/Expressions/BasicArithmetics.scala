package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.IntAnswer

object BasicArithmetics {
  trait IntAnswerCanDoBasicArithmetics extends BasicArithmetics[IntAnswer] {
    import IntAnswerCanDoBasicArithmetics._
    def plus(x: IntAnswer, y: IntAnswer): IntAnswer = IntAnswer(combine(x, y)(_ + _))
    def minus(x: IntAnswer, y: IntAnswer): IntAnswer = IntAnswer(combine(x, y)(_ - _))
    def times(x: IntAnswer, y: IntAnswer): IntAnswer = IntAnswer(combine(x, y)(_ * _))
    def div(x: IntAnswer, y: IntAnswer): IntAnswer = IntAnswer(combine(x, y)(_ / _))
    def negate(x: IntAnswer) = IntAnswer(x.possibleValue.map(-_))
  }
  implicit object IntAnswerCanDoBasicArithmetics extends IntAnswerCanDoBasicArithmetics {
    private def combine(x: IntAnswer, y: IntAnswer)(f: (Int, Int) => Int): Option[Int] = for {
      thisValue <- x.possibleValue
      thatValue <- y.possibleValue
    } yield f(thisValue, thatValue)
  }
}

trait BasicArithmetics[SubType<:Answer] {
  def plus(x: SubType, y: SubType): SubType
  def minus(x: SubType, y: SubType): SubType
  def times(x: SubType, y: SubType): SubType
  def div(x: SubType, y: SubType): SubType
  def negate(x: SubType): SubType

  class Ops(left: SubType) {
    def +(right: SubType): SubType = plus(left, right)
    def -(right: SubType): SubType = minus(left, right)
    def *(right: SubType): SubType = times(left, right)
    def /(right: SubType): SubType = div(left, right)
    def unary_-(): SubType = negate(left)
  }

  implicit def mkNumericOps(lhs: SubType): Ops = new Ops(lhs)
}
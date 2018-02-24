package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.{ DecAnswer, IntAnswer }

import scala.language.implicitConversions

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
  
  trait DecAnswerCanDoBasicArithmetics extends BasicArithmetics[DecAnswer] {
    import DecAnswerCanDoBasicArithmetics._
    def plus(x: DecAnswer, y: DecAnswer): DecAnswer = DecAnswer(combine(x, y)(_ + _))
    def minus(x: DecAnswer, y: DecAnswer): DecAnswer = DecAnswer(combine(x, y)(_ - _))
    def times(x: DecAnswer, y: DecAnswer): DecAnswer = DecAnswer(combine(x, y)(_ * _))
    def div(x: DecAnswer, y: DecAnswer): DecAnswer = DecAnswer(combine(x, y)(_ / _))
    def negate(x: DecAnswer) = DecAnswer(x.possibleValue.map(-_))
  }
  implicit object DecAnswerCanDoBasicArithmetics extends DecAnswerCanDoBasicArithmetics {
    private def combine(x: DecAnswer, y: DecAnswer)(f: (BigDecimal, BigDecimal) => BigDecimal): Option[BigDecimal] = for {
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
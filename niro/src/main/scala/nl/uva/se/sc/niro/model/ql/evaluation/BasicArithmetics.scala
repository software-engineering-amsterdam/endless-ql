package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

// format: off
object BasicArithmetics {
  trait IntAnswerCanDoBasicArithmetics extends BasicArithmetics[IntegerAnswer] {
    def plus(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.combine(i)(_ + _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ + _))
      case d: MoneyAnswer => MoneyAnswer(x.combine(d)(_ + _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def minus(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.combine(i)(_ - _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ - _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def times(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.combine(i)(_ * _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ * _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def div(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.combine(i)(_ / _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ / _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def negate(x: IntegerAnswer): Answer = IntegerAnswer(x.possibleValue.map(-_))
  }
  implicit object IntAnswerCanDoBasicArithmetics extends IntAnswerCanDoBasicArithmetics

  trait DecAnswerCanDoBasicArithmetics extends BasicArithmetics[DecimalAnswer] {
    def plus(x: DecimalAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => DecimalAnswer(x.combine(i)(_ + _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ + _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def minus(x: DecimalAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => DecimalAnswer(x.combine(i)(_ - _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ - _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def times(x: DecimalAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => DecimalAnswer(x.combine(i)(_ * _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ * _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def div(x: DecimalAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => DecimalAnswer(x.combine(i)(_ / _))
      case d: DecimalAnswer => DecimalAnswer(x.combine(d)(_ / _))
      case _ => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def negate(x: DecimalAnswer): Answer = DecimalAnswer(x.possibleValue.map(-_))
  }

  implicit object DecAnswerCanDoBasicArithmetics extends DecAnswerCanDoBasicArithmetics
}
// format: on

trait BasicArithmetics[SubType <: Answer] {
  def plus(x: SubType, y: Answer): Answer
  def minus(x: SubType, y: Answer): Answer
  def times(x: SubType, y: Answer): Answer
  def div(x: SubType, y: Answer): Answer
  def negate(x: SubType): Answer

  class Ops(left: SubType) {
    def +(right: Answer): Answer = plus(left, right)
    def -(right: Answer): Answer = minus(left, right)
    def *(right: Answer): Answer = times(left, right)
    def /(right: Answer): Answer = div(left, right)
    def unary_-(): Answer = negate(left)
  }

  implicit def mkNumericOps(left: SubType): Ops = new Ops(left)
}

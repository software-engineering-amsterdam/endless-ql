package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

object BasicArithmetics {
  trait IntAnswerCanDoBasicArithmetics extends BasicArithmetics[IntegerAnswer] {
    def plus(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.value + i.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def subtract(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.value - i.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x - $y")
    }
    def multiply(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.value * i.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x * $y")
    }
    def div(x: IntegerAnswer, y: Answer): Answer = y match {
      case i: IntegerAnswer => IntegerAnswer(x.value / i.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x / $y")
    }
    def minus(x: IntegerAnswer): Answer = IntegerAnswer(-x.value)
  }
  implicit object IntAnswerCanDoBasicArithmetics extends IntAnswerCanDoBasicArithmetics

  trait DecAnswerCanDoBasicArithmetics extends BasicArithmetics[DecimalAnswer] {
    def plus(x: DecimalAnswer, y: Answer): Answer = y match {
      case d: DecimalAnswer => DecimalAnswer(x.value + d.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x + $y")
    }
    def subtract(x: DecimalAnswer, y: Answer): Answer = y match {
      case d: DecimalAnswer => DecimalAnswer(x.value - d.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x - $y")
    }
    def multiply(x: DecimalAnswer, y: Answer): Answer = y match {
      case d: DecimalAnswer => DecimalAnswer(x.value * d.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x * $y")
    }
    def div(x: DecimalAnswer, y: Answer): Answer = y match {
      case d: DecimalAnswer => DecimalAnswer(x.value / d.value)
      case _                => throw new IllegalArgumentException(s"Can't perform operation $x / $y")
    }
    def minus(x: DecimalAnswer): Answer = DecimalAnswer(-x.value)
  }

  implicit object DecAnswerCanDoBasicArithmetics extends DecAnswerCanDoBasicArithmetics
}

trait BasicArithmetics[SubType <: Answer] {
  def plus(x: SubType, y: Answer): Answer
  def subtract(x: SubType, y: Answer): Answer
  def multiply(x: SubType, y: Answer): Answer
  def div(x: SubType, y: Answer): Answer
  def minus(x: SubType): Answer

  class Ops(left: SubType) {
    def +(right: Answer): Answer = plus(left, right)
    def -(right: Answer): Answer = subtract(left, right)
    def *(right: Answer): Answer = multiply(left, right)
    def /(right: Answer): Answer = div(left, right)
    def unary_-(): Answer = minus(left)
  }

  implicit def mkNumericOps(left: SubType): Ops = new Ops(left)
}

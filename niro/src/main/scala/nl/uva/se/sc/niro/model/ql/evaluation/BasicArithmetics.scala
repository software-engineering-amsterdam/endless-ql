package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer }

import scala.language.implicitConversions

// format: off
object BasicArithmetics {
  trait IntAnswerCanDoBasicArithmetics extends BasicArithmetics[IntegerAnswer] {
    def plus(x: IntegerAnswer, y: Answer): IntegerAnswer = y match { case i: IntegerAnswer => IntegerAnswer(x.value + i.value) }
    def minus(x: IntegerAnswer, y: Answer): IntegerAnswer = y match { case i: IntegerAnswer => IntegerAnswer(x.value - i.value) }
    def times(x: IntegerAnswer, y: Answer): IntegerAnswer = y match { case i: IntegerAnswer => IntegerAnswer(x.value * i.value) }
    def div(x: IntegerAnswer, y: Answer): IntegerAnswer = y match { case i: IntegerAnswer => IntegerAnswer(x.value / i.value) }
    def negate(x: IntegerAnswer): IntegerAnswer = IntegerAnswer(-x.value)
  }
  implicit object IntAnswerCanDoBasicArithmetics extends IntAnswerCanDoBasicArithmetics

  trait DecAnswerCanDoBasicArithmetics extends BasicArithmetics[DecimalAnswer] {
    def plus(x: DecimalAnswer, y: Answer): DecimalAnswer = y match { case d: DecimalAnswer => DecimalAnswer(x.value + d.value) }
    def minus(x: DecimalAnswer, y: Answer): DecimalAnswer = y match { case d: DecimalAnswer => DecimalAnswer(x.value - d.value) }
    def times(x: DecimalAnswer, y: Answer): DecimalAnswer = y match { case d: DecimalAnswer => DecimalAnswer(x.value * d.value) }
    def div(x: DecimalAnswer, y: Answer): DecimalAnswer = y match { case d: DecimalAnswer => DecimalAnswer(x.value / d.value) }
    def negate(x: DecimalAnswer): DecimalAnswer = DecimalAnswer(-x.value)
  }
  implicit object DecAnswerCanDoBasicArithmetics extends DecAnswerCanDoBasicArithmetics
}
// format: on

trait BasicArithmetics[SubType <: Answer] {
  def plus(x: SubType, y: Answer): SubType
  def minus(x: SubType, y: Answer): SubType
  def times(x: SubType, y: Answer): SubType
  def div(x: SubType, y: Answer): SubType
  def negate(x: SubType): SubType

  class Ops(left: SubType) {
    def +(right: Answer): SubType = plus(left, right)
    def -(right: Answer): SubType = minus(left, right)
    def *(right: Answer): SubType = times(left, right)
    def /(right: Answer): SubType = div(left, right)
    def unary_-(): SubType = negate(left)
  }

  implicit def mkNumericOps(left: SubType): Ops = new Ops(left)
}

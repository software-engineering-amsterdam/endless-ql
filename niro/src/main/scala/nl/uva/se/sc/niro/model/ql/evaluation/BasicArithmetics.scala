package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

// format: off
object BasicArithmetics {
  trait IntAnswerCanDoBasicArithmetics extends BasicArithmetics[IntegerAnswer] {
    def plus(x: IntegerAnswer, y: Answer): Answer = y match { case i: IntegerAnswer => IntegerAnswer(x.value + i.value) }
    def subtract(x: IntegerAnswer, y: Answer): Answer = y match { case i: IntegerAnswer => IntegerAnswer(x.value - i.value) }
    def multiply(x: IntegerAnswer, y: Answer): Answer = y match { case i: IntegerAnswer => IntegerAnswer(x.value * i.value) }
    def div(x: IntegerAnswer, y: Answer): Answer = y match { case i: IntegerAnswer => IntegerAnswer(x.value / i.value) }
    def minus(x: IntegerAnswer): Answer = IntegerAnswer(-x.value)
  }
  implicit object IntAnswerCanDoBasicArithmetics extends IntAnswerCanDoBasicArithmetics

  trait DecAnswerCanDoBasicArithmetics extends BasicArithmetics[DecimalAnswer] {
    def plus(x: DecimalAnswer, y: Answer): Answer = y match { case d: DecimalAnswer => DecimalAnswer(x.value + d.value) }
    def subtract(x: DecimalAnswer, y: Answer): Answer = y match { case d: DecimalAnswer => DecimalAnswer(x.value - d.value) }
    def multiply(x: DecimalAnswer, y: Answer): Answer = y match { case d: DecimalAnswer => DecimalAnswer(x.value * d.value) }
    def div(x: DecimalAnswer, y: Answer): Answer = y match { case d: DecimalAnswer => DecimalAnswer(x.value / d.value) }
    def minus(x: DecimalAnswer): Answer = DecimalAnswer(-x.value)
  }
  implicit object DecAnswerCanDoBasicArithmetics extends DecAnswerCanDoBasicArithmetics

  trait MoneyAnswerCanDoBasicArithmetics extends BasicArithmetics[MoneyAnswer] {
    def plus(x: MoneyAnswer, y: Answer): Answer = y match { case d: MoneyAnswer => MoneyAnswer(x.value + d.value) }
    def subtract(x: MoneyAnswer, y: Answer): Answer = y match { case d: MoneyAnswer => MoneyAnswer(x.value - d.value) }
    def multiply(x: MoneyAnswer, y: Answer): Answer = y match { case d: MoneyAnswer => MoneyAnswer(x.value * d.value) }
    def div(x: MoneyAnswer, y: Answer): Answer = y match { case d: MoneyAnswer => MoneyAnswer(x.value / d.value) }
    def minus(x: MoneyAnswer): Answer = MoneyAnswer(-x.value)
  }
  implicit object MoneyAnswerCanDoBasicArithmetics extends MoneyAnswerCanDoBasicArithmetics
}
// format: on

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

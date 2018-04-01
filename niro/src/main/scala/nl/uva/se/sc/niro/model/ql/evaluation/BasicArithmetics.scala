package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

// format: off
object BasicArithmetics {
  trait IntAnswerCanDoBasicArithmetics extends BasicArithmetics[IntegerAnswer] {
    def plus(left: IntegerAnswer, right: Answer): Answer = right match { case i: IntegerAnswer => IntegerAnswer(left.value + i.value) }
    def subtract(left: IntegerAnswer, right: Answer): Answer = right match { case i: IntegerAnswer => IntegerAnswer(left.value - i.value) }
    def multiply(left: IntegerAnswer, right: Answer): Answer = right match { case i: IntegerAnswer => IntegerAnswer(left.value * i.value) }
    def div(left: IntegerAnswer, right: Answer): Answer = right match { case i: IntegerAnswer => IntegerAnswer(left.value / i.value) }
    def minus(left: IntegerAnswer): Answer = IntegerAnswer(-left.value)
  }
  implicit object IntAnswerCanDoBasicArithmetics extends IntAnswerCanDoBasicArithmetics

  trait DecAnswerCanDoBasicArithmetics extends BasicArithmetics[DecimalAnswer] {
    def plus(left: DecimalAnswer, right: Answer): Answer = right match { case d: DecimalAnswer => DecimalAnswer(left.value + d.value) }
    def subtract(left: DecimalAnswer, right: Answer): Answer = right match { case d: DecimalAnswer => DecimalAnswer(left.value - d.value) }
    def multiply(left: DecimalAnswer, right: Answer): Answer = right match { case d: DecimalAnswer => DecimalAnswer(left.value * d.value) }
    def div(left: DecimalAnswer, right: Answer): Answer = right match { case d: DecimalAnswer => DecimalAnswer(left.value / d.value) }
    def minus(left: DecimalAnswer): Answer = DecimalAnswer(-left.value)
  }
  implicit object DecAnswerCanDoBasicArithmetics extends DecAnswerCanDoBasicArithmetics

  trait MoneyAnswerCanDoBasicArithmetics extends BasicArithmetics[MoneyAnswer] {
    def plus(left: MoneyAnswer, right: Answer): Answer = right match { case d: MoneyAnswer => MoneyAnswer(left.value + d.value) }
    def subtract(left: MoneyAnswer, right: Answer): Answer = right match { case d: MoneyAnswer => MoneyAnswer(left.value - d.value) }
    def multiply(left: MoneyAnswer, right: Answer): Answer = right match { case d: MoneyAnswer => MoneyAnswer(left.value * d.value) }
    def div(left: MoneyAnswer, right: Answer): Answer = right match { case d: MoneyAnswer => MoneyAnswer(left.value / d.value) }
    def minus(left: MoneyAnswer): Answer = MoneyAnswer(-left.value)
  }
  implicit object MoneyAnswerCanDoBasicArithmetics extends MoneyAnswerCanDoBasicArithmetics
}
// format: on

trait BasicArithmetics[SubType <: Answer] {
  def plus(left: SubType, right: Answer): Answer
  def subtract(left: SubType, right: Answer): Answer
  def multiply(left: SubType, right: Answer): Answer
  def div(left: SubType, right: Answer): Answer
  def minus(left: SubType): Answer

  class Ops(left: SubType) {
    def +(right: Answer): Answer = plus(left, right)
    def -(right: Answer): Answer = subtract(left, right)
    def *(right: Answer): Answer = multiply(left, right)
    def /(right: Answer): Answer = div(left, right)
    def unary_-(): Answer = minus(left)
  }

  implicit def mkNumericOps(left: SubType): Ops = new Ops(left)
}

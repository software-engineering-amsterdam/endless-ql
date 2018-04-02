package nl.uva.se.sc.niro.ql.model.ast.evaluation

import nl.uva.se.sc.niro.ql.model.ast.expressions.answers._

import scala.language.implicitConversions

// format: off
object Orderings {
  trait IntAnswerCanDoOrderings extends Orderings[IntegerAnswer] {
    def lt(left: IntegerAnswer, right: Answer): BooleanAnswer = right match { case i: IntegerAnswer => BooleanAnswer(left.value < i.value) }
    def lte(left: IntegerAnswer, right: Answer): BooleanAnswer = right match { case i: IntegerAnswer => BooleanAnswer(left.value <= i.value) }
    def gte(left: IntegerAnswer, right: Answer): BooleanAnswer = right match { case i: IntegerAnswer => BooleanAnswer(left.value >= i.value) }
    def gt(left: IntegerAnswer, right: Answer): BooleanAnswer = right match { case i: IntegerAnswer => BooleanAnswer(left.value > i.value) }
    def neq(left: IntegerAnswer, right: Answer): BooleanAnswer = right match { case i: IntegerAnswer => BooleanAnswer(left.value != i.value) }
    def equ(left: IntegerAnswer, right: Answer): BooleanAnswer = right match { case i: IntegerAnswer => BooleanAnswer(left.value == i.value) }
  }
  implicit object IntAnswerCanDoOrderings extends IntAnswerCanDoOrderings

  trait DecAnswerCanDoOrderings extends Orderings[DecimalAnswer] {
    def lt(left: DecimalAnswer, right: Answer): BooleanAnswer = right match { case d: DecimalAnswer => BooleanAnswer(left.value < d.value) }
    def lte(left: DecimalAnswer, right: Answer): BooleanAnswer = right match { case d: DecimalAnswer => BooleanAnswer(left.value <= d.value) }
    def gte(left: DecimalAnswer, right: Answer): BooleanAnswer = right match { case d: DecimalAnswer => BooleanAnswer(left.value >= d.value) }
    def gt(left: DecimalAnswer, right: Answer): BooleanAnswer = right match { case d: DecimalAnswer => BooleanAnswer(left.value > d.value) }
    def neq(left: DecimalAnswer, right: Answer): BooleanAnswer = right match { case d: DecimalAnswer => BooleanAnswer(left.value != d.value) }
    def equ(left: DecimalAnswer, right: Answer): BooleanAnswer = right match { case d: DecimalAnswer => BooleanAnswer(left.value == d.value) }
  }
  implicit object DecAnswerCanDoOrderings extends DecAnswerCanDoOrderings

  trait BooleanAnswerCanDoOrderings extends Orderings[BooleanAnswer] {
    def lt(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value < b.value) }
    def lte(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value <= b.value) }
    def gte(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value >= b.value) }
    def gt(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value > b.value) }
    def neq(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value != b.value) }
    def equ(left: BooleanAnswer, right: Answer): BooleanAnswer = right match { case b: BooleanAnswer => BooleanAnswer(left.value == b.value) }
  }
  implicit object BooleanAnswerCanDoOrderings extends BooleanAnswerCanDoOrderings

  trait StringAnswerCanDoOrderings extends Orderings[StringAnswer] {
    def lt(left: StringAnswer, right: Answer): BooleanAnswer = right match { case s: StringAnswer => BooleanAnswer(left.value <  s.value ) }
    def lte(left: StringAnswer, right: Answer): BooleanAnswer = right match { case s: StringAnswer => BooleanAnswer(left.value <= s.value ) }
    def gte(left: StringAnswer, right: Answer): BooleanAnswer = right match { case s: StringAnswer => BooleanAnswer(left.value >= s.value ) }
    def gt(left: StringAnswer, right: Answer): BooleanAnswer = right match { case s: StringAnswer => BooleanAnswer(left.value >  s.value ) }
    def neq(left: StringAnswer, right: Answer): BooleanAnswer = right match { case s: StringAnswer => BooleanAnswer(left.value != s.value ) }
    def equ(left: StringAnswer, right: Answer): BooleanAnswer = right match { case s: StringAnswer => BooleanAnswer(left.value == s.value ) }
  }
  implicit object StringAnswerCanDoOrderings extends StringAnswerCanDoOrderings

  trait DateAnswerCanDoOrderings extends Orderings[DateAnswer] {
    def lt(left: DateAnswer, right: Answer): BooleanAnswer = right match { case d: DateAnswer => BooleanAnswer(left.value.isBefore(d.value)) }
    def lte(left: DateAnswer, right: Answer): BooleanAnswer = right match { case d: DateAnswer => BooleanAnswer(!left.value.isAfter(d.value)) }
    def gte(left: DateAnswer, right: Answer): BooleanAnswer = right match { case d: DateAnswer => BooleanAnswer(!left.value.isBefore(d.value)) }
    def gt(left: DateAnswer, right: Answer): BooleanAnswer = right match { case d: DateAnswer => BooleanAnswer(left.value.isAfter(d.value)) }
    def neq(left: DateAnswer, right: Answer): BooleanAnswer = right match { case d: DateAnswer => BooleanAnswer(!left.value.isEqual(d.value)) }
    def equ(left: DateAnswer, right: Answer): BooleanAnswer = right match { case d: DateAnswer => BooleanAnswer(left.value.isEqual(d.value)) }
  }
  implicit object DateAnswerCanDoOrderings extends DateAnswerCanDoOrderings

  trait MoneyAnswerCanDoOrderings extends Orderings[MoneyAnswer] {
    def lt(left: MoneyAnswer, right: Answer): BooleanAnswer = right match { case m: MoneyAnswer => BooleanAnswer(left.value < m.value) }
    def lte(left: MoneyAnswer, right: Answer): BooleanAnswer = right match { case m: MoneyAnswer => BooleanAnswer(left.value <= m.value) }
    def gte(left: MoneyAnswer, right: Answer): BooleanAnswer = right match { case m: MoneyAnswer => BooleanAnswer(left.value >= m.value) }
    def gt(left: MoneyAnswer, right: Answer): BooleanAnswer = right match { case m: MoneyAnswer => BooleanAnswer(left.value > m.value) }
    def neq(left: MoneyAnswer, right: Answer): BooleanAnswer = right match { case m: MoneyAnswer => BooleanAnswer(left.value != m.value) }
    def equ(left: MoneyAnswer, right: Answer): BooleanAnswer = right match { case m: MoneyAnswer => BooleanAnswer(left.value == m.value) }
  }
  implicit object MoneyAnswerCanDoOrderings extends MoneyAnswerCanDoOrderings
}
// format: on

trait Orderings[SubType <: Answer] {
  def lt(left: SubType, right: Answer): Answer
  def lte(left: SubType, right: Answer): Answer
  def gte(left: SubType, right: Answer): Answer
  def gt(left: SubType, right: Answer): Answer
  def neq(left: SubType, right: Answer): Answer
  def equ(left: SubType, right: Answer): Answer

  class Ops(left: SubType) {
    def <(right: Answer): Answer = lt(left, right)
    def <=(right: Answer): Answer = lte(left, right)
    def >=(right: Answer): Answer = gte(left, right)
    def >(right: Answer): Answer = gt(left, right)
    def !==(right: Answer): Answer = neq(left, right)
    def ===(right: Answer): Answer = equ(left, right)
  }

  implicit def mkOrderingOps(left: SubType): Ops = new Ops(left)
}

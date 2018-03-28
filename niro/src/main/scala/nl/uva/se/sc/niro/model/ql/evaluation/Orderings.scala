package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql.expressions.answers._

import scala.language.implicitConversions

// format: off
object Orderings {
  trait IntAnswerCanDoOrderings extends Orderings[IntegerAnswer] {
    def lt(x: IntegerAnswer, y: Answer): BooleanAnswer = y match { case i: IntegerAnswer => BooleanAnswer(x.value < i.value) }
    def lte(x: IntegerAnswer, y: Answer): BooleanAnswer = y match { case i: IntegerAnswer => BooleanAnswer(x.value <= i.value) }
    def gte(x: IntegerAnswer, y: Answer): BooleanAnswer = y match { case i: IntegerAnswer => BooleanAnswer(x.value >= i.value) }
    def gt(x: IntegerAnswer, y: Answer): BooleanAnswer = y match { case i: IntegerAnswer => BooleanAnswer(x.value > i.value) }
    def neq(x: IntegerAnswer, y: Answer): BooleanAnswer = y match { case i: IntegerAnswer => BooleanAnswer(x.value != i.value) }
    def equ(x: IntegerAnswer, y: Answer): BooleanAnswer = y match { case i: IntegerAnswer => BooleanAnswer(x.value == i.value) }
  }
  implicit object IntAnswerCanDoOrderings extends IntAnswerCanDoOrderings

  trait DecAnswerCanDoOrderings extends Orderings[DecimalAnswer] {
    def lt(x: DecimalAnswer, y: Answer): BooleanAnswer = y match { case d: DecimalAnswer => BooleanAnswer(x.value < d.value) }
    def lte(x: DecimalAnswer, y: Answer): BooleanAnswer = y match { case d: DecimalAnswer => BooleanAnswer(x.value <= d.value) }
    def gte(x: DecimalAnswer, y: Answer): BooleanAnswer = y match { case d: DecimalAnswer => BooleanAnswer(x.value >= d.value) }
    def gt(x: DecimalAnswer, y: Answer): BooleanAnswer = y match { case d: DecimalAnswer => BooleanAnswer(x.value > d.value) }
    def neq(x: DecimalAnswer, y: Answer): BooleanAnswer = y match { case d: DecimalAnswer => BooleanAnswer(x.value != d.value) }
    def equ(x: DecimalAnswer, y: Answer): BooleanAnswer = y match { case d: DecimalAnswer => BooleanAnswer(x.value == d.value) }
  }
  implicit object DecAnswerCanDoOrderings extends DecAnswerCanDoOrderings

  trait BooleanAnswerCanDoOrderings extends Orderings[BooleanAnswer] {
    def lt(x: BooleanAnswer, y: Answer): BooleanAnswer = y match { case b: BooleanAnswer => BooleanAnswer(x.value < b.value) }
    def lte(x: BooleanAnswer, y: Answer): BooleanAnswer = y match { case b: BooleanAnswer => BooleanAnswer(x.value <= b.value) }
    def gte(x: BooleanAnswer, y: Answer): BooleanAnswer = y match { case b: BooleanAnswer => BooleanAnswer(x.value >= b.value) }
    def gt(x: BooleanAnswer, y: Answer): BooleanAnswer = y match { case b: BooleanAnswer => BooleanAnswer(x.value > b.value) }
    def neq(x: BooleanAnswer, y: Answer): BooleanAnswer = y match { case b: BooleanAnswer => BooleanAnswer(x.value != b.value) }
    def equ(x: BooleanAnswer, y: Answer): BooleanAnswer = y match { case b: BooleanAnswer => BooleanAnswer(x.value == b.value) }
  }
  implicit object BooleanAnswerCanDoOrderings extends BooleanAnswerCanDoOrderings

  trait StringAnswerCanDoOrderings extends Orderings[StringAnswer] {
    def lt(x: StringAnswer, y: Answer): BooleanAnswer = y match { case s: StringAnswer => BooleanAnswer(x.value <  s.value ) }
    def lte(x: StringAnswer, y: Answer): BooleanAnswer = y match { case s: StringAnswer => BooleanAnswer(x.value <= s.value ) }
    def gte(x: StringAnswer, y: Answer): BooleanAnswer = y match { case s: StringAnswer => BooleanAnswer(x.value >= s.value ) }
    def gt(x: StringAnswer, y: Answer): BooleanAnswer = y match { case s: StringAnswer => BooleanAnswer(x.value >  s.value ) }
    def neq(x: StringAnswer, y: Answer): BooleanAnswer = y match { case s: StringAnswer => BooleanAnswer(x.value != s.value ) }
    def equ(x: StringAnswer, y: Answer): BooleanAnswer = y match { case s: StringAnswer => BooleanAnswer(x.value == s.value ) }
  }
  implicit object StringAnswerCanDoOrderings extends StringAnswerCanDoOrderings

  trait DateAnswerCanDoOrderings extends Orderings[DateAnswer] {
    def lt(x: DateAnswer, y: Answer): BooleanAnswer = y match { case d: DateAnswer => BooleanAnswer(x.value.isBefore(d.value)) }
    def lte(x: DateAnswer, y: Answer): BooleanAnswer = y match { case d: DateAnswer => BooleanAnswer(!x.value.isAfter(d.value)) }
    def gte(x: DateAnswer, y: Answer): BooleanAnswer = y match { case d: DateAnswer => BooleanAnswer(!x.value.isBefore(d.value)) }
    def gt(x: DateAnswer, y: Answer): BooleanAnswer = y match { case d: DateAnswer => BooleanAnswer(x.value.isAfter(d.value)) }
    def neq(x: DateAnswer, y: Answer): BooleanAnswer = y match { case d: DateAnswer => BooleanAnswer(!x.value.isEqual(d.value)) }
    def equ(x: DateAnswer, y: Answer): BooleanAnswer = y match { case d: DateAnswer => BooleanAnswer(x.value.isEqual(d.value)) }
  }
  implicit object DateAnswerCanDoOrderings extends DateAnswerCanDoOrderings

  trait MoneyAnswerCanDoOrderings extends Orderings[MoneyAnswer] {
    def lt(x: MoneyAnswer, y: Answer): BooleanAnswer = y match { case m: MoneyAnswer => BooleanAnswer(x.value < m.value) }
    def lte(x: MoneyAnswer, y: Answer): BooleanAnswer = y match { case m: MoneyAnswer => BooleanAnswer(x.value <= m.value) }
    def gte(x: MoneyAnswer, y: Answer): BooleanAnswer = y match { case m: MoneyAnswer => BooleanAnswer(x.value >= m.value) }
    def gt(x: MoneyAnswer, y: Answer): BooleanAnswer = y match { case m: MoneyAnswer => BooleanAnswer(x.value > m.value) }
    def neq(x: MoneyAnswer, y: Answer): BooleanAnswer = y match { case m: MoneyAnswer => BooleanAnswer(x.value != m.value) }
    def equ(x: MoneyAnswer, y: Answer): BooleanAnswer = y match { case m: MoneyAnswer => BooleanAnswer(x.value == m.value) }
  }
  implicit object MoneyAnswerCanDoOrderings extends MoneyAnswerCanDoOrderings
}
// format: on

trait Orderings[SubType <: Answer] {
  def lt(x: SubType, y: Answer): Answer
  def lte(x: SubType, y: Answer): Answer
  def gte(x: SubType, y: Answer): Answer
  def gt(x: SubType, y: Answer): Answer
  def neq(x: SubType, y: Answer): Answer
  def equ(x: SubType, y: Answer): Answer

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

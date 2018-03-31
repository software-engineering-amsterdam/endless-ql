package nl.uva.se.sc.niro.ql.model.ast.expressions

import nl.uva.se.sc.niro.model.ql.expressions.Conversions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }

object Widener {
  def widen(left: Answer, right: Answer): (Answer, Answer) = (left, right) match {
    case (i: IntegerAnswer, _: DecimalAnswer) => (i.toDecimal, right)
    case (i: IntegerAnswer, _: MoneyAnswer)   => (i.toMoney, right)
    case (_: DecimalAnswer, i: IntegerAnswer) => (left, i.toDecimal)
    case (d: DecimalAnswer, _: MoneyAnswer)   => (d.toMoney, right)
    case (_: MoneyAnswer, i: IntegerAnswer)   => (left, i.toMoney)
    case (_: MoneyAnswer, d: DecimalAnswer)   => (left, d.toMoney)
    case _                                    => (left, right)
  }
}

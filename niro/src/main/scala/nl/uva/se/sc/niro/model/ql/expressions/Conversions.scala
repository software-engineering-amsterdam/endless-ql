package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer, MoneyAnswer }
import Conversions._

import scala.language.implicitConversions

object Conversions {
  implicit class IntAnswerConversions(integerAnswer: IntegerAnswer) {
    def toDecimal = DecimalAnswer(BigDecimal(integerAnswer.value))
    def toMoney = MoneyAnswer(BigDecimal(integerAnswer.value))
  }

  implicit class DecimalAnswerConversions(decimalAnswer: DecimalAnswer) {
    def toMoney = MoneyAnswer(decimalAnswer.value)
  }
}

object Widener {
  def widen(left: Answer, right: Answer): (Answer, Answer) = (left, right) match {
    case (i: IntegerAnswer, _: DecimalAnswer) => (i.toDecimal, right)
    case (i: IntegerAnswer, _: MoneyAnswer)   => (i.toDecimal, right)
    case (_: DecimalAnswer, i: IntegerAnswer) => (left, i.toDecimal)
    case (d: DecimalAnswer, _: MoneyAnswer)   => (d.toMoney, right)
    case (_: MoneyAnswer, i: IntegerAnswer)   => (left, i.toMoney)
    case (_: MoneyAnswer, d: DecimalAnswer)   => (left, d.toMoney)
    case _                                    => (left, right)
  }
}

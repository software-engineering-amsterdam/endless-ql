package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ DecimalAnswer, IntegerAnswer, MoneyAnswer }

import scala.language.implicitConversions

object Conversions {
  implicit class IntAnswerToDecAnswer(integerAnswer: IntegerAnswer) {
    def toDecimal = DecimalAnswer(BigDecimal(integerAnswer.value))
  }
}

package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ DecimalAnswer, IntegerAnswer }

import scala.language.implicitConversions

object ImplicitConversions {
  implicit def integerAnswerToDecimalAnswer(integerAnswer: IntegerAnswer): DecimalAnswer =
    DecimalAnswer(BigDecimal(integerAnswer.value))

  implicit def integerAnswerDecimalAnswerWiden(integerAnswer: IntegerAnswer, decimalAnswer: DecimalAnswer): (DecimalAnswer, DecimalAnswer) =
    (DecimalAnswer(BigDecimal(integerAnswer.value)), decimalAnswer)

  implicit def decimalAnswerIntegerAnswerWiden(decimalAnswer: DecimalAnswer, integerAnswer: IntegerAnswer): (DecimalAnswer, DecimalAnswer) =
    (decimalAnswer, DecimalAnswer(BigDecimal(integerAnswer.value)))
}

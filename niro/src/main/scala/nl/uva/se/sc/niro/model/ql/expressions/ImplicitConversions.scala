package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ DecimalAnswer, IntegerAnswer, StringAnswer }

import scala.language.implicitConversions

object ImplicitConversions {
  implicit def integerAnswerToDecimalAnswer(integerAnswer: IntegerAnswer): DecimalAnswer =
    DecimalAnswer(BigDecimal(integerAnswer.value))

  implicit def integerAnswerDecimalAnswerWiden(integerAnswer: IntegerAnswer, decimalAnswer: DecimalAnswer): (DecimalAnswer, DecimalAnswer) =
    (DecimalAnswer(BigDecimal(integerAnswer.value)), decimalAnswer)

  implicit def integerAnswerStringAnswerWiden(integerAnswer: IntegerAnswer, stringAnswer: StringAnswer): (StringAnswer, StringAnswer) =
    (StringAnswer(integerAnswer.value.toString), stringAnswer)

  implicit def stringAnswerIntegerAnswerWiden(stringAnswer: StringAnswer, integerAnswer: IntegerAnswer): (StringAnswer, StringAnswer) =
  (stringAnswer, StringAnswer(integerAnswer.value.toString))

  implicit def decimalAnswerStringAnswerWiden(decimalAnswer: DecimalAnswer, stringAnswer: StringAnswer): (StringAnswer, StringAnswer) =
    (StringAnswer(decimalAnswer.value.toString), stringAnswer)

  implicit def stringAnswerDecimalAnswerWiden(stringAnswer: StringAnswer, decimalAnswer: DecimalAnswer): (StringAnswer, StringAnswer) =
    (stringAnswer, StringAnswer(decimalAnswer.value.toString))
}

package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ DecimalAnswer, IntegerAnswer }

import scala.language.implicitConversions

object ImplicitConversions {
  implicit def intAnswerToDecAnswer(integerAnswer: IntegerAnswer): DecimalAnswer =
    DecimalAnswer(BigDecimal(integerAnswer.value))
}

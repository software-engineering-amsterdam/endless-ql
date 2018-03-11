package nl.uva.se.sc.niro.model.expressions

import nl.uva.se.sc.niro.model.expressions.answers.{ DecimalAnswer, IntegerAnswer }

import scala.language.implicitConversions

object ImplicitConversions {
  implicit def intAnswerToDecAnswer(value: IntegerAnswer): DecimalAnswer =
    DecimalAnswer(value.possibleValue.map(BigDecimal(_)))
}

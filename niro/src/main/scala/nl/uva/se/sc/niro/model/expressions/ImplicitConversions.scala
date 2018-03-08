package nl.uva.se.sc.niro.model.expressions

import nl.uva.se.sc.niro.model.expressions.answers.{ DecAnswer, IntAnswer }

import scala.language.implicitConversions

object ImplicitConversions {
  implicit def intAnswerToDecAnswer(value: IntAnswer): DecAnswer = DecAnswer(value.possibleValue.map(BigDecimal(_)))
}

package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.{ DecAnswer, IntAnswer }

import scala.language.implicitConversions

object ImplicitConversions {
  implicit def intAnswerToDecAnswer(value: IntAnswer): DecAnswer = DecAnswer(value.possibleValue.map(_.toDouble))
}

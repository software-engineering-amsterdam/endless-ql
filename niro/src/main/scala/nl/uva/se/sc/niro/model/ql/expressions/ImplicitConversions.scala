package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.typecheckexpressions.{ Answer, DoubleValue, StringValue }

import scala.language.implicitConversions

object ImplicitConversions {
  implicit def intValueToDoubleValue(intValue: Answer[Int]): Answer[Double] =
    DoubleValue(intValue.get.toDouble)
}

package nl.uva.se.sc.niro.qls.parser.typecheck

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.{ AnswerType, IntegerType }
import nl.uva.se.sc.niro.qls.parser.typecheck.SliderRange.checkIntegerValue

trait RangeCheck {

  def checkRange(
      name: String,
      answerType: AnswerType,
      minimum: Double,
      maximum: Double): Seq[Option[TypeCheckError]] = {
    if (answerType == IntegerType) {
      Seq(
        checkRange("SliderRangeCheck", minimum, maximum),
        checkIntegerValue(name, minimum, "minimum"),
        checkIntegerValue(name, maximum, "maximum"))
    } else Seq(checkRange("SliderRangeCheck", minimum, maximum))
  }

  def checkRange(name: String, minimum: Double, maximum: Double): Option[TypeCheckError] = {
    if (minimum >= maximum)
      Some(TypeCheckError(name, s"The minimum value '$minimum' should be less then the maximum value '$maximum'."))
    else None
  }

}

package nl.uva.se.sc.niro.qls.parser.typecheck

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.AnswerType

trait StepSizeCheck {

  def stepCheck(
      name: String,
      answerType: AnswerType,
      minimum: Double,
      maximum: Double,
      stepSize: Double): Seq[Option[TypeCheckError]] = {
    Seq(rangeCheck(name, maximum - minimum, stepSize))
  }

  def rangeCheck(name: String, range: Double, stepSize: Double): Option[TypeCheckError] = {
    if (stepSize > range)
      Some(
        TypeCheckError(
          name,
          s"The step size '$stepSize' is to large, it is not possible to perform steps within range."))
    else None
  }

}

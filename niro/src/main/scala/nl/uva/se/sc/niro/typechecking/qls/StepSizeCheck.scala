package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.AnswerType

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
    if (stepSize >= range / 2)
      Some(
        TypeCheckError(
          name,
          s"The stepsize [${stepSize}] is to large, it is not possible to have more then two steps."))
    else None
  }

}

package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.{ IntegerType, QLForm }
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet
import nl.uva.se.sc.niro.qls.model.ast.style.SpinBox

object SpinRange extends RangeCheck with StepSizeCheck with IntegerValueCheck {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val invalidSpinRanges = stylesheet
      .collectAllQuestions()
      .flatMap(question =>
        question.styling.widgetType match {
          case Some(SpinBox(minimum, maximum, stepSize)) =>
            val answerType = form.symbolTable(question.name).answerType
            val rangeErrors = checkRange("SpinRangeCheck", answerType, minimum, maximum) ++
              stepCheck("spinRangeCheck", answerType, minimum, maximum, stepSize)
            if (answerType == IntegerType) {
              rangeErrors ++ Seq(checkIntegerValue("SpinRangeCheck", stepSize, "stepSize"))
            } else {
              rangeErrors
            }
          case _ => Seq.empty
      })
      .flatten

    if (invalidSpinRanges.isEmpty) {
      Right(stylesheet)
    } else {
      Left(invalidSpinRanges)
    }
  }

}

package nl.uva.se.sc.niro.qls.parser.typecheck

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.{ IntegerType, QLForm }
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet
import nl.uva.se.sc.niro.qls.model.ast.style.SpinBox
import org.apache.logging.log4j.scala.Logging

object SpinRange extends RangeCheck with StepSizeCheck with IntegerValueCheck with Logging {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    logger.info("Check spinner range, step-size and numeric types configuration.")
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

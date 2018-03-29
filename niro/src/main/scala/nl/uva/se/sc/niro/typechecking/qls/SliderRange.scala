package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.{ AnswerType, IntegerType, QLForm }
import nl.uva.se.sc.niro.model.qls.style.Slider
import nl.uva.se.sc.niro.model.qls.{ QLStylesheet, Question }

object SliderRange {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val invalidSliderRanges: Seq[TypeCheckError] = stylesheet
      .collectAllQuestions()
      .flatMap(question => checkRange(form.symbolTable(question.name).answerType, question))
    if (invalidSliderRanges.isEmpty) {
      Right(stylesheet)
    } else {
      Left(invalidSliderRanges)
    }
  }

  def checkRange(answerType: AnswerType, question: Question): Seq[TypeCheckError] = {
    question.styling.widgetType match {
      case Some(Slider(minimum, maximum)) => {
        val rangeError: Seq[Option[TypeCheckError]] = Seq(checkRange(minimum, maximum))
        if (answerType == IntegerType) {
          val sliderErrors: Seq[Option[TypeCheckError]] = rangeError ++ Seq(
            checkIntegerValue(minimum, "minimum"),
            checkIntegerValue(maximum, "maximum"))
          sliderErrors.flatten
        } else rangeError.flatten
      }
      case _ => Seq.empty
    }
  }

  private def checkRange(minimum: Double, maximum: Double): Option[TypeCheckError] = {
    if (minimum >= maximum)
      Some(
        TypeCheckError(
          "SliderRangeCheck",
          s"The minimum value [${minimum}] should be less then the maximum value [${maximum}]."))
    else None
  }

  private def checkIntegerValue(value: Double, name: String): Option[TypeCheckError] = {
    if (value.toInt != value)
      Some(TypeCheckError("SliderRangeCheck", s"The ${name} value [${value}] is not an integer value."))
    else None
  }
}

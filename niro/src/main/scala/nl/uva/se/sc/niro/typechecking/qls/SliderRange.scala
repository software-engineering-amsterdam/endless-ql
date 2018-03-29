package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import nl.uva.se.sc.niro.model.qls.style.Slider

object SliderRange extends RangeCheck with IntegerValueCheck {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val invalidSliderRanges = stylesheet
      .collectAllQuestions()
      .flatMap(question =>
        question.styling.widgetType match {
          case Some(Slider(minimum, maximum)) =>
            val answerType = form.symbolTable(question.name).answerType
            checkRange("SliderRangeCheck", answerType, minimum, maximum)
          case _ => Seq.empty
      })
      .flatten

    if (invalidSliderRanges.isEmpty) {
      Right(stylesheet)
    } else {
      Left(invalidSliderRanges)
    }
  }

}

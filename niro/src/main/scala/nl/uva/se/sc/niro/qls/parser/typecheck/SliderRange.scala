package nl.uva.se.sc.niro.qls.parser.typecheck

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet
import nl.uva.se.sc.niro.qls.model.ast.style.Slider
import org.apache.logging.log4j.scala.Logging

object SliderRange extends RangeCheck with IntegerValueCheck with Logging {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    logger.info("Check slider range and numeric types configuration.")
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

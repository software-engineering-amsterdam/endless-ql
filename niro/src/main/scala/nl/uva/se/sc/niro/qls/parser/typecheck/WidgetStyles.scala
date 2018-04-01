package nl.uva.se.sc.niro.qls.parser.typecheck

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.{ AnswerType, QLForm }
import nl.uva.se.sc.niro.qls.model.ast.{ QLStylesheet, Styling }
import org.apache.logging.log4j.scala.Logging

object WidgetStyles extends Logging {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    logger.info("Check widget style is compatible with question type.")
    val allWidgetStyles: Seq[(AnswerType, Styling)] =
      stylesheet.defaultStyles.toSeq ++
        stylesheet.pages.flatMap(_.defaultStyles.toSeq) ++
        stylesheet.pages.flatMap(_.sections.flatMap(_.defaultStyles.toSeq)) ++
        stylesheet
          .collectAllQuestions()
          .filter(_.styling.widgetType.isDefined)
          .map(q => (form.symbolTable(q.name).answerType, q.styling))

    val incompatibleWidgetStyles = allWidgetStyles.filterNot(stylingIsCompatible)

    if (incompatibleWidgetStyles.isEmpty) {
      Right(stylesheet)
    } else {
      Left(
        incompatibleWidgetStyles
          .map((widgetStyling) =>
            TypeCheckError(
              "WidgetStylingError",
              s"The styling '${widgetStyling._2.widgetType
                .getOrElse("Default")}' is not compatible with the widgets type '${widgetStyling._1}'."
          )))
    }
  }

  def stylingIsCompatible(widgetStyle: (AnswerType, Styling)): Boolean =
    widgetStyle._2.widgetType.forall(_.isCompatibleWith(widgetStyle._1))

}

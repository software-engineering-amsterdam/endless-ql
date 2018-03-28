package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.{ AnswerType, QLForm }
import nl.uva.se.sc.niro.model.qls.{ QLStylesheet, Styling }

object WidgetStyles {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val allWidgetStyles: Seq[(AnswerType, Styling)] =
      stylesheet.defaultStyles.toSeq ++
        stylesheet.pages.flatMap(_.defaultStyles.toSeq) ++
        stylesheet.pages.flatMap(_.sections.flatMap(_.defaultStyles.toSeq)) ++
        stylesheet
          .collectAllQuestions()
          .filter(_.widgetType.widgetType.isDefined)
          .map(q => (form.symbolTable(q.name).answerType, q.widgetType))

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

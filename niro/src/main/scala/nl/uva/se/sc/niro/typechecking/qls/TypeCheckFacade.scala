package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import org.apache.logging.log4j.scala.Logging

object TypeCheckFacade extends Logging {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] =
    for {
      _ <- FormStylesheetNames.check(form, stylesheet)
      _ <- References.check(form, stylesheet)
      _ <- WidgetStyles.check(form, stylesheet)
      _ <- QuestionSinglePlacing.check(form, stylesheet)
      _ <- SliderRange.check(form, stylesheet)
      _ <- SpinRange.check(form, stylesheet)
    } yield stylesheet

}

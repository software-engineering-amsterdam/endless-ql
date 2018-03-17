package nl.uva.se.sc.niro.typechecking

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import org.apache.logging.log4j.scala.Logging

// TODO check if this is the correct name, maybe facade is better
object QLSTypeChecker extends Logging {

  def pipeline(form: QLForm, stylesheet: QLStylesheet): Either[TypeCheckError, QLStylesheet] =
    for {
      _ <- checkReferences(form, stylesheet)
    } yield stylesheet

  def checkReferences(form: QLForm, stylesheet: QLStylesheet): Either[TypeCheckError, QLStylesheet] =
    // TODO implement
    Right(stylesheet)
}

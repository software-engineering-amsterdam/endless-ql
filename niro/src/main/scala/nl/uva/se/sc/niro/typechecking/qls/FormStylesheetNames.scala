package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet

object FormStylesheetNames {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    if (form.formName == stylesheet.name) {
      Right(stylesheet)
    } else {
      Left(
        Seq(
          TypeCheckError(
            "Name Check Error",
            s"The name of the stylesheet '${stylesheet.name}' does not match that of the form '${form.formName}'.")))
    }
  }

}

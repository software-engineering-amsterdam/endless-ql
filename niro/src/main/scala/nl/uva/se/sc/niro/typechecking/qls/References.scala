package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.{ QLForm, Statement }
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet

object References {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val questionsInStylesheet = stylesheet.collectAllQuestions()
    val uniqueQuestionNamesInStylesheet = questionsInStylesheet.map(_.name).toSet

    val questionsOnForm = Statement.collectAllQuestions(form.statements)
    val uniqueQuestionNamesOnForm = questionsOnForm.map(_.id).toSet

    if (uniqueQuestionNamesInStylesheet == uniqueQuestionNamesOnForm) {
      Right(stylesheet)
    } else {
      val unreferencedQuestionOnForm = uniqueQuestionNamesOnForm -- uniqueQuestionNamesInStylesheet
      val unreferencedQuestionInStylesheet = uniqueQuestionNamesInStylesheet -- uniqueQuestionNamesOnForm

      Left(
        unreferencedQuestionOnForm
          .map(name =>
            TypeCheckError("ReferenceCheck", s"Question '$name' is defined in QL but not used in the QLS file."))
          .toSeq ++
          unreferencedQuestionInStylesheet
            .map(
              name =>
                TypeCheckError(
                  "ReferenceCheck",
                  s"Question '$name' is referenced in the QLS file but have not been defined in the QL file."
              ))
            .toSeq)

    }
  }

}

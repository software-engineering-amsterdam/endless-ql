package nl.uva.se.sc.niro.typechecking

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.{ QLForm, Statement }
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import org.apache.logging.log4j.scala.Logging

object QLSTypeCheckFacade extends Logging {

  def performChecks(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] =
    for {
      _ <- checkFormStylesheetNames(form, stylesheet)
      _ <- checkReferences(form, stylesheet)
    } yield stylesheet

  def checkFormStylesheetNames(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
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

  def checkReferences(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val questionsInStylesheet = stylesheet.collectAllQuestions()
    val uniqueQuestionNamesInStylesheet = questionsInStylesheet.map(_.name).toSet

    val questionsOnForm = Statement.collectAllQuestions(form.statements)
    val uniqueQuestionNamesOnForm = questionsOnForm.map(_.id).toSet

    if (uniqueQuestionNamesInStylesheet == uniqueQuestionNamesOnForm) {
      Right(stylesheet)
    } else {
      val unreferencedQuestionOnForm = uniqueQuestionNamesOnForm -- uniqueQuestionNamesInStylesheet
      val unreferencedQuestionInStylesheet = uniqueQuestionNamesInStylesheet -- uniqueQuestionNamesOnForm

      Left(unreferencedQuestionOnForm
        .map(name =>
          TypeCheckError(
            "ReferenceCheck",
            s"Question ${questionsOnForm.filter(_.id == name).map(_.id)} is defined in QL but not used in the QLS file."))
        .toSeq ++
        unreferencedQuestionInStylesheet
          .map(name =>
            TypeCheckError(
              "ReferenceCheck",
              s"Question ${questionsInStylesheet.filter(_.name == name).map(_.name)} is referenced in the QLS file but have not been defined in the QL file."
          ))
          .toSeq)

    }
  }
}

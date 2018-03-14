package nl.uva.se.sc.niro.typechecking

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.{ QLForm, Statement }
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import org.apache.logging.log4j.scala.Logging

// TODO check if this is the correct name, maybe facade is better
object QLSTypeChecker extends Logging {

  def pipeline(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] =
    for {
      _ <- checkReferences(form, stylesheet)
    } yield stylesheet

  def checkReferences(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val questionInStylesheet = stylesheet
      .collectAllQuestions()
      .filter(question => !form.symbolTable.isDefinedAt(question.name))
    val questionsOnForm = Statement.collectAllQuestions(form.statements)

    val uniqueQuestionNamesInStylesheet = questionInStylesheet.map(_.name).toSet
    val uniqueQuestionNamesOnForm = questionsOnForm.map(_.id).toSet
    if (uniqueQuestionNamesInStylesheet == uniqueQuestionNamesOnForm) {
      Right(stylesheet)
    } else {
      if (uniqueQuestionNamesOnForm.size > uniqueQuestionNamesInStylesheet.size) {
        val unreferencedQuestionOnForm = uniqueQuestionNamesOnForm -- uniqueQuestionNamesInStylesheet
        Left(
          unreferencedQuestionOnForm
            .map(name =>
              TypeCheckError(
                "ReferenceCheck",
                s"Question(s) ${questionsOnForm.filter(_.id == name).map(_.id)} is/are defined in QL but not used in the QLS file."))
            .toSeq)
      } else {
        val unreferencedQuestionInStylesheet = uniqueQuestionNamesInStylesheet -- uniqueQuestionNamesOnForm
        Left(
          unreferencedQuestionInStylesheet
            .map(name =>
              TypeCheckError(
                "ReferenceCheck",
                s"Question(s) ${questionInStylesheet.filter(_.name == name).map(_.name)} is/are referenced in the QLS file but have not been defined in thte QL file."
            ))
            .toSeq)
      }

    }
  }
}

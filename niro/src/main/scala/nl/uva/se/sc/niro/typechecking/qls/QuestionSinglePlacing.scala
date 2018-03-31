package nl.uva.se.sc.niro.typechecking.qls

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet

object QuestionSinglePlacing {

  def check(form: QLForm, stylesheet: QLStylesheet): Either[Seq[TypeCheckError], QLStylesheet] = {
    val noneSingleQuestions = stylesheet.collectAllQuestions().groupBy(_.name).filter(_._2.size > 1)

    if (noneSingleQuestions.isEmpty) {
      Right(stylesheet)
    } else {
      Left(
        noneSingleQuestions.keys
          .map(
            questionName =>
              TypeCheckError(
                "QuestionPlacingError",
                s"Question '$questionName' appeared more then once in the QLS file."
            ))
          .toSeq)
    }
  }
}

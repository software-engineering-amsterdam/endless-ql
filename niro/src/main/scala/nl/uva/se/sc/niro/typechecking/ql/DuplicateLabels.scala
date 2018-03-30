package nl.uva.se.sc.niro.typechecking.ql

import nl.uva.se.sc.niro.errors.Warning
import nl.uva.se.sc.niro.model.ql.{ QLForm, Question, Statement }
import org.apache.logging.log4j.scala.Logging

object DuplicateLabels extends Logging {

  def check(qLForm: QLForm): QLForm = {
    logger.info("Phase 6 - Checking duplicate question labels ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val questionsWithDuplicateLabels: Seq[Seq[Question]] =
      questions.groupBy(_.label).valuesIterator.filter(_.size > 1).toList

    val warnings = questionsWithDuplicateLabels
      .map(
        duplicates =>
          Warning(
            message = s"Questions ${duplicates.map(_.id).mkString(", ")} have duplicate label: ${duplicates.head.label}"
        ))

    qLForm.copy(warnings = warnings)
  }
}

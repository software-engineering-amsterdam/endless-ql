package nl.uva.se.sc.niro.typechecking.ql

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.errors.Warning
import nl.uva.se.sc.niro.model.ql.{ QLForm, Question, Statement }
import org.apache.logging.log4j.scala.Logging

object DuplicateQuestionChecker extends Logging {
  def checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] = {
    logger.info("Phase 4 - Checking duplicate question declarations with different types ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val duplicateQuestions: Iterator[Seq[Question]] = questions.groupBy(_.id).valuesIterator.filter(_.size > 1)

    val duplicateQuestionsWithDifferentTypes: Seq[Seq[Question]] = duplicateQuestions
      .filter(questionsWithMultipleOccurrences => questionsWithMultipleOccurrences.map(_.answerType).toSet.size > 1)
      .toList

    if (duplicateQuestionsWithDifferentTypes.nonEmpty) {
      duplicateQuestionsWithDifferentTypes
        .map(duplicateQuestions =>
          TypeCheckError(message = s"Duplicate question declarations with different types: ${duplicateQuestions.map(q => s"${q.id} -> ${q.answerType}").mkString(", ")}"))
        .asLeft
    } else {
      qLForm.asRight
    }
  }

  def checkDuplicateLabels(qLForm: QLForm): QLForm = {
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

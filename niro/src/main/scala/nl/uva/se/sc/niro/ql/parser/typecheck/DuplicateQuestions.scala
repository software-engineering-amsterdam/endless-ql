package nl.uva.se.sc.niro.ql.parser.typecheck

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.{ QLForm, Question, Statement }
import org.apache.logging.log4j.scala.Logging

object DuplicateQuestions extends Logging {
  def check(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] = {
    logger.info("Phase 4 - Checking duplicate question declarations with different types ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val duplicateQuestions: Iterator[Seq[Question]] = questions.groupBy(_.id).valuesIterator.filter(_.size > 1)

    val duplicateQuestionsWithDifferentTypes: Seq[Seq[Question]] = duplicateQuestions
      .filter(questionsWithMultipleOccurrences => questionsWithMultipleOccurrences.map(_.answerType).toSet.size > 1)
      .toList

    if (duplicateQuestionsWithDifferentTypes.nonEmpty) {
      duplicateQuestionsWithDifferentTypes
        .map(duplicateQuestions =>
          TypeCheckError(message =
            s"Duplicate question declarations with different types: ${duplicateQuestions.map(q => s"${q.id} -> ${q.answerType}").mkString(", ")}"))
        .asLeft
    } else {
      qLForm.asRight
    }
  }

}

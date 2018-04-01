package qls.validators

import qls.models.ast._
import qls.collectors._

import scala.collection.JavaConversions._

case class DuplicateQuestionPlacement(label: String) extends Exception(label)

object DuplicateQuestionPlacementValidator {
  def check(qls: Statement): Option[Exception] = {
    val questions = ElementCollector.getQuestions(qls)

    getDuplicateQuestions(questions)
      .map(doubleDeclared => {
        val identifier = doubleDeclared.identifier
        val message = s"Question '${identifier.id}' is declared multiple times in QLS"
        return Some(new DuplicateQuestionPlacement(message))
      })
    None
  }

  def getDuplicateQuestions(questions: List[Question]): List[Question] = {
    questions.diff(questions.distinct)
  }
}

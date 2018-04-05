package qls.validators

import qls.models.ast._
import qls.collectors._

import scala.collection.JavaConversions._

case class DuplicateQuestionPlacement(label: String) extends Exception(label)

class DuplicateQuestionPlacementValidator extends QLSValidator {
  def execute(qls: Root): Unit = {
    val questions = ElementCollector.getQuestions(qls)

    getDuplicateQuestions(questions)
      .map(doubleDeclared => {
        val identifier = doubleDeclared.identifier
        val message = s"Question '${identifier.id}' is declared multiple times in QLS"
        throw new DuplicateQuestionPlacement(message)
      })
  }

  def getDuplicateQuestions(questions: List[Question]): List[Question] = {
    questions.diff(questions.distinct)
  }
}

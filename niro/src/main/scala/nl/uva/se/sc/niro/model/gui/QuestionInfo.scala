package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.{ AnswerType, Question }
import nl.uva.se.sc.niro.model.Expressions.{ Answer, Expression }

class QuestionInfo(id: String, answerType: AnswerType, label: String, isReadOnly: Boolean) {
  var answer: Option[Answer] = None
  var isVisible: Boolean = true
}

object QuestionInfo {
  def apply(visible: Expression, question: Question): QuestionInfo = {
    new QuestionInfo(question.id, question.answerType, question.label, false)
  }
}
package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.{ AnswerType, Question }
import nl.uva.se.sc.niro.model.expressions.{ Answer, Expression }

class GUIQuestion(id: String, answerType: AnswerType, label: String, isReadOnly: Boolean) {
  var answer: Option[Answer] = None
  var isVisible: Boolean = true
}

object GUIQuestion {
  def apply(visible: Expression, question: Question): GUIQuestion = {
    new GUIQuestion(question.id, question.answerType, question.label, false)
  }
}
package nl.uva.se.sc.niro.model.qls.style

import nl.uva.se.sc.niro.model.ql.AnswerType

abstract class WidgetType extends Style {
  def isCompatibleWith(answerType: AnswerType): Boolean
}


package nl.uva.se.sc.niro.qls.model.ast.style

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

abstract class WidgetType extends Style {
  def isCompatibleWith(answerType: AnswerType): Boolean
}

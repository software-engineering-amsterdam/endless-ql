package nl.uva.se.sc.niro.model.qls.style

import nl.uva.se.sc.niro.model.ql.{ AnswerType, BooleanType }

case class CheckBox() extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean = answerType == BooleanType
}

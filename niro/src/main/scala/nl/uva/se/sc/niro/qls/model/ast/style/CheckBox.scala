package nl.uva.se.sc.niro.qls.model.ast.style

import nl.uva.se.sc.niro.ql.model.ast.{ AnswerType, BooleanType }

case class CheckBox() extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean = answerType == BooleanType
}

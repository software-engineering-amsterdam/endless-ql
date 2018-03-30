package nl.uva.se.sc.niro.model.qls.style

import nl.uva.se.sc.niro.model.ql.{ AnswerType, BooleanType }

case class ComboBox(trueValue: String, falseValue: String) extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean = answerType == BooleanType
}

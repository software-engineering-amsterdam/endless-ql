package nl.uva.se.sc.niro.model.qls.style

import nl.uva.se.sc.niro.model.ql.{ AnswerType, DecimalType, IntegerType, MoneyType }

case class Slider() extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean =
    answerType == IntegerType || answerType == DecimalType || answerType == MoneyType
}

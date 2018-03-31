package nl.uva.se.sc.niro.qls.model.ast.style

import nl.uva.se.sc.niro.ql.model.ast.{ AnswerType, DecimalType, IntegerType, MoneyType }

case class SpinBox(minimum: Double, maximum: Double, stepSize: Double) extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean =
    answerType == IntegerType || answerType == DecimalType || answerType == MoneyType
}

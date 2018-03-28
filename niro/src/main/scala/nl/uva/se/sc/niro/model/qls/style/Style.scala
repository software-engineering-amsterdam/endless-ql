package nl.uva.se.sc.niro.model.qls.style

import nl.uva.se.sc.niro.model.ql.{ AnswerType, BooleanType, IntegerType }

abstract class Style

abstract class WidgetType extends Style {
  def isCompatibleWith(answerType: AnswerType): Boolean
}
case class SpinBox() extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean = answerType == IntegerType
}
case class CheckBox() extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean = answerType == BooleanType
}
case class Radio(trueValue: String, falseValue: String) extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean = answerType == BooleanType
}
case class ComboBox(trueValue: String, falseValue: String) extends WidgetType {
  override def isCompatibleWith(answerType: AnswerType): Boolean = answerType == BooleanType
}

case class FontType(name: String) extends Style
case class FontSize(size: Int) extends Style
case class Color(color: String) extends Style
case class Width(width: Int) extends Style

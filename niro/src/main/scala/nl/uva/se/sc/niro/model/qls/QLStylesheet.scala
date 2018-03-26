package nl.uva.se.sc.niro.model.qls

import nl.uva.se.sc.niro.model.ql.{ AnswerType, BooleanType, IntegerType }

/**
  * QLS AST
  */
case class QLStylesheet(name: String, pages: Seq[Page], defaultStyles: Map[AnswerType, Styling]) {

  def collectAllQuestions(): Seq[Question] = pages.flatMap(_.sections.flatMap(_.questions))

  def collectQuestionsForPage(pageName: String): Seq[Question] =
    pages.filter(_.name == pageName).flatMap(_.sections.flatMap(_.questions))
}

case class Page(name: String, sections: Seq[Section], defaultStyles: Map[AnswerType, Styling])

case class Section(name: String, questions: Seq[Question], defaultStyles: Map[AnswerType, Styling])

case class Question(name: String, widgetType: Styling)

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

case class Styling(
    widgetType: Option[WidgetType] = None,
    width: Option[Width] = None,
    color: Option[Color] = None,
    fontType: Option[FontType] = None,
    fontSize: Option[FontSize] = None) {

  def ++(that: Styling): Styling = {
    Styling(
      that.widgetType.orElse(widgetType),
      that.width.orElse(width),
      that.color.orElse(color),
      that.fontType.orElse(fontType),
      that.fontSize.orElse(fontSize))
  }
}

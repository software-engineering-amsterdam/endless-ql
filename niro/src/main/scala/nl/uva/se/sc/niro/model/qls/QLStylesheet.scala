package nl.uva.se.sc.niro.model.qls

import nl.uva.se.sc.niro.model.ql.AnswerType

/**
  * QLS AST
  */
case class QLStylesheet(name: String, pages: Seq[Page], defaultStyles: Map[AnswerType, WidgetType]) {

  def collectAllQuestions(): Seq[Question] = pages.flatMap(_.sections.flatMap(_.questions))

  def collectQuestionsForPage(pageName: String): Seq[Question] =
    pages.filter(_.name == pageName).flatMap(_.sections.flatMap(_.questions))
}

case class Page(name: String, sections: Seq[Section], defaultStyles: Map[AnswerType, WidgetType])

case class Section(name: String, questions: Seq[Question], defaultStyles: Map[AnswerType, WidgetType])

case class Question(name: String, widgetType: Option[WidgetType])

abstract class WidgetType
case class SpinBox() extends WidgetType
case class CheckBox() extends WidgetType
case class Radio(trueValue: String, falseValue: String) extends WidgetType
case class ComboBox(trueValue: String, falseValue: String) extends WidgetType

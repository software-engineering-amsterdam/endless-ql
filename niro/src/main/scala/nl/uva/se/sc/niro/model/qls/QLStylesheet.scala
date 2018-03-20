package nl.uva.se.sc.niro.model.qls

case class QLStylesheet(name: String, pages: Seq[Page]) {

  def collectStylesForQuesiotn(id: String): Seq[WidgetType] = {
    pages.flatMap(_.sections.flatMap(_.questions.filter(_.name == id).map(_.widgetType.orNull)))
  }

  def collectPageNamesWithQuestion(questionId: String): Seq[String] =
    pages.filter(containsSectionWithQuestion(_, questionId)).map(_.name)

  def collectAllQuestions(): Seq[Question] = pages.flatMap(_.sections.flatMap(_.questions))

  def collectQuestionsForPage(pageName: String): Seq[Question] =
    pages.filter(_.name == pageName).flatMap(_.sections.flatMap(_.questions))

  private def containsSectionWithQuestion(page: Page, questionId: String): Boolean = {
    page.sections.exists(containsQuestion(_, questionId))
  }

  private def containsQuestion(section: Section, questionId: String): Boolean = {
    section.questions.exists(_.name == questionId)
  }
}

case class Page(name: String, sections: Seq[Section])

case class Section(name: String, questions: Seq[Question])

case class Question(name: String, widgetType: Option[WidgetType])

abstract class WidgetType
case class SpinBox() extends WidgetType
case class CheckBox() extends WidgetType
case class Radio(trueValue: String, falseValue: String) extends WidgetType

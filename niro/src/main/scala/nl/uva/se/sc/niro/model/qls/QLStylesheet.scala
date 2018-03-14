package nl.uva.se.sc.niro.model.qls

case class QLStylesheet(name: String, pages: Seq[Page]) {
  
  def getPageNamesWithQuestion(questionId: String): Seq[String] =
    pages.filter(containsSectionWithQuestion(_, questionId)).map(_.name)

  private def containsSectionWithQuestion(page: Page, questionId: String) = {
    page.sections.exists(containsQuestion(_, questionId))
  }

  private def containsQuestion(section: Section, questionId: String) = {
    section.questions.exists(_.name == questionId)
  }
}

case class Page(name: String, sections: Seq[Section])

case class Section(name: String, questions: Seq[Question])

case class Question(name: String)

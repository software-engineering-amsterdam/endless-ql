package nl.uva.se.sc.niro.model.qls

case class QLStylesheet(name: String, pages: Seq[Page]) {
  def getPageNamesWithQuestion(questionId: String): Seq[String] =
    pages.filter(_.sections.filter(_.questions.filter(_.name == questionId).nonEmpty).nonEmpty).map(_.name)
}

case class Page(name: String, sections: Seq[Section])

case class Section(name: String, questions: Seq[Question])

case class Question(name: String)

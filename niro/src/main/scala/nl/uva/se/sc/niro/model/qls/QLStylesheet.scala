package nl.uva.se.sc.niro.model.qls

case class QLStylesheet(name: String, pages: List[Page])

case class Page(name: String, sections: Seq[Section])

case class Section(name: String, questions: Seq[Question])

case class Question(name: String)

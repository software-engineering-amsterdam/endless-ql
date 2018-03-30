package nl.uva.se.sc.niro.model.qls

import nl.uva.se.sc.niro.model.ql.AnswerType

case class Page(name: String, sections: Seq[Section], defaultStyles: Map[AnswerType, Styling]) {

  def collectAllQuestions(): Seq[Question] = {
    sections.flatMap(_.collectAllQuestions)
  }
}

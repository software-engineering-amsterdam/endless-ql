package nl.uva.se.sc.niro.qls.model.ast

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class Page(name: String, sections: Seq[Section], defaultStyles: Map[AnswerType, Styling]) {

  def collectAllQuestions(): Seq[Question] = {
    sections.flatMap(_.collectAllQuestions())
  }
}

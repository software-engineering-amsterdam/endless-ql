package nl.uva.se.sc.niro.qls.model.ast

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class Section(name: String, statements: Seq[Statement], defaultStyles: Map[AnswerType, Styling])
    extends Statement {

  def collectAllQuestions(): Seq[Question] = {
    statements.flatMap({
      case q: Question => Seq(q)
      case s: Section  => s.collectAllQuestions()
    })
  }
}

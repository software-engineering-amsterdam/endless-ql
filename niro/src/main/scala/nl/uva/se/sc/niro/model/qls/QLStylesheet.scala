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

package nl.uva.se.sc.niro.qls.model.ast

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

/**
  * QLS AST
  */
case class QLStylesheet(name: String, pages: Seq[Page], defaultStyles: Map[AnswerType, Styling]) {

  def collectAllQuestions(): Seq[Question] = pages.flatMap(_.collectAllQuestions())

  def collectQuestionsForPage(pageName: String): Seq[Question] =
    pages.filter(_.name == pageName).flatten(_.collectAllQuestions())
}

package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.model.ql.{ And, Eq, Or, Question }
import nl.uva.se.sc.niro.model.ql.expressions.{ BinaryOperation, Expression, Reference }
import nl.uva.se.sc.niro.model.ql.expressions.answers.StringAnswer
import nl.uva.se.sc.niro.model.qls.QLStylesheet

object PageVisibilityFactory {
  val ACTIVE_PAGE_NAME = "__active_page_name__"

  def createPageVisibility(question: Question, stylesheet: Option[QLStylesheet], visible: Expression) = {
    val pageNames = getPageNamesContainingQuestion(question, stylesheet)
    val pageNameChecks = createPageCheckExpressions(pageNames)
    createCumulativePageNameCheck(pageNameChecks).map(BinaryOperation(And, _, visible)).getOrElse(visible)
  }

  private def getPageNamesContainingQuestion(question: Question, stylesheet: Option[QLStylesheet]): Seq[String] = {
    stylesheet.map(_.getPageNamesWithQuestion(question.id)).getOrElse(Seq.empty)
  }

  private def createPageCheckExpressions(pageNames: Seq[String]): Seq[BinaryOperation] = {
    pageNames.map(pageName => BinaryOperation(Eq, Reference(ACTIVE_PAGE_NAME), StringAnswer(pageName)))
  }

  private def createCumulativePageNameCheck(pageNameChecks: Seq[BinaryOperation]): Option[BinaryOperation] = {
    pageNameChecks match {
      case head +: Seq() => Some(head)
      case head +: tail  => Some(tail.foldLeft(head)((l: Expression, r: Expression) => BinaryOperation(Or, l, r)))
      case Seq()         => None
    }
  }

}

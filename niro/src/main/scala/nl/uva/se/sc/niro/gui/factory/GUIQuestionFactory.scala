package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.model.gui.GUIQuestion
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, StringAnswer }
import nl.uva.se.sc.niro.model.ql.{ And, Eq, Or, Question }
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import org.apache.logging.log4j.scala.Logging

object GUIQuestionFactory extends Logging {
  // TODO de-dup this constant, see QLFormController
  private val ACTIVE_PAGE_NAME = "__active_page_name__"

  def makeGUIQuestion(visible: Expression, question: Question, stylesheet: Option[QLStylesheet]): GUIQuestion = {
    // 1: Find page names that contain this question
    val pageNames = stylesheet.map(_.getPagesWithQuestion(question.id)).getOrElse(Seq.empty)
    // 2: Convert page names to logical expression
    val pageNameChecks = pageNames.map(pageName => BinaryOperation(Eq ,Reference(ACTIVE_PAGE_NAME), StringAnswer(pageName)))
    // 3: Create logical 'or' expression for these expressions
    val pageExpression = pageNameChecks match {
      case head +: Seq() => Some(head)
      case head +: tail  => Some(tail.foldLeft(head)((l: Expression, r: Expression) => BinaryOperation(Or,l, r)))
      case Seq()         => None
    }
    // 4: Combine this 'or' expression using a 'and' expression with 'visible'
    val pageVisibility = pageExpression.map(BinaryOperation(And, _, visible)).getOrElse(visible)
    GUIQuestion(question.id, question.answerType, question.label, isReadOnly(question.expression), pageVisibility)
  }

  private def isReadOnly(expression: Expression): Boolean =
    expression match {
      case answer: Answer => answer.possibleValue.isDefined
      case _              => true
    }
}
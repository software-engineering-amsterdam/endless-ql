package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.model.Question
import nl.uva.se.sc.niro.model.expressions._
import nl.uva.se.sc.niro.model.expressions.answers.Answer
import nl.uva.se.sc.niro.model.gui.GUIQuestion
import org.apache.logging.log4j.scala.Logging

object GUIQuestionFactory extends Logging {

  def makeGUIQuestion(visible: Expression, question: Question): GUIQuestion = {
    GUIQuestion(question.id, question.answerType, question.label, isReadOnly(question.expression), visible)
  }

  private def isReadOnly(expression: Expression): Boolean =
    expression match {
      case a: Answer => a.possibleValue.isDefined
      case _         => true
    }
}
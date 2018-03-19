package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.model.gui.GUIQuestion
import nl.uva.se.sc.niro.model.ql.Question
import nl.uva.se.sc.niro.model.ql.expressions._
import org.apache.logging.log4j.scala.Logging

object GUIQuestionFactory extends Logging {

  def makeGUIQuestion(visible: Expression, question: Question): GUIQuestion = {
    QLGUIQuestion(question.id, question.answerType, question.label, question.expression.isDefined, visible)
  }
}

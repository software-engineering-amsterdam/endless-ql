package nl.uva.se.sc.niro.ql.model.gui

import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import org.apache.logging.log4j.scala.Logging

object QuestionFactory extends Logging {

  def makeGUIQuestion(visible: Expression, question: nl.uva.se.sc.niro.ql.model.ast.Question): Question = {
    QLQuestion(question.id, question.answerType, question.label, question.expression.isDefined, visible)
  }
}

package nl.uva.se.sc.niro.ql.model.gui

import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import org.apache.logging.log4j.scala.Logging

object QuestionFactory extends Logging {

  def makeGUIQuestion(
      visible: Expression,
      question: nl.uva.se.sc.niro.ql.model.ast.Question): nl.uva.se.sc.niro.ql.model.gui.Question = {
    QLQuestion(
      id = question.id,
      answerType = question.answerType,
      label = question.label,
      isReadOnly = question.expression.isDefined,
      visibility = visible)
  }
}

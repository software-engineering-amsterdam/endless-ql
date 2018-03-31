package nl.uva.se.sc.niro.ql.model

import nl.uva.se.sc.niro.ql.model.ast.Question
import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import nl.uva.se.sc.niro.ql.model.gui.{ GUIQuestion, QLGUIQuestion }
import org.apache.logging.log4j.scala.Logging

object GUIQuestionFactory extends Logging {

  def makeGUIQuestion(visible: Expression, question: Question): GUIQuestion = {
    QLGUIQuestion(
      id = question.id,
      answerType = question.answerType,
      label = question.label,
      isReadOnly = question.expression.isDefined,
      visibility = visible)
  }
}

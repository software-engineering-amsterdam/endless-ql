package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType
import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import nl.uva.se.sc.niro.ql.model.gui.Question

case class QLSGUIQuestion(
    override val id: String,
    override val answerType: AnswerType,
    override val label: String,
    override val isReadOnly: Boolean,
    override val visibility: Expression,
    styling: GUIStyling
) extends Question(id, answerType, label, isReadOnly, visibility, component = None)

object QLSGUIQuestion {

  def apply(question: Question, styling: GUIStyling) =
    new QLSGUIQuestion(
      question.id,
      question.answerType,
      question.label,
      question.isReadOnly,
      question.visibility,
      styling)
}

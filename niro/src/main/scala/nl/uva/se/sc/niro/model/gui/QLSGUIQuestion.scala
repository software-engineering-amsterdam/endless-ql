package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.ql.expressions.Expression

case class QLSGUIQuestion(
  override val id: String,
  override val answerType: AnswerType,
  override val label: String,
  override val isReadOnly: Boolean,
  override val visibility: Expression,
  styling: GUIStyling
) extends GUIQuestion(id, answerType, label, isReadOnly, visibility, component = None)

object QLSGUIQuestion {

  def apply(question: GUIQuestion) =
    new QLSGUIQuestion(
      question.id,
      question.answerType,
      question.label,
      question.isReadOnly,
      question.visibility,
      styling = GUIStyling())

  def apply(question: GUIQuestion, styling: GUIStyling) =
    new QLSGUIQuestion(
      question.id,
      question.answerType,
      question.label,
      question.isReadOnly,
      question.visibility,
      styling)
}

package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.gui.control.Component
import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.ql.expressions.Expression

abstract class GUIQuestion(
    val id: String,
    val answerType: AnswerType,
    val label: String,
    val isReadOnly: Boolean,
    val visibility: Expression,
    var component: Option[Component[_]]
)

case class QLGUIQuestion(
    override val id: String,
    override val answerType: AnswerType,
    override val label: String,
    override val isReadOnly: Boolean,
    override val visibility: Expression
) extends GUIQuestion(id, answerType, label, isReadOnly, visibility, component = None)

case class QLSGUIQuestion(
    override val id: String,
    override val answerType: AnswerType,
    override val label: String,
    override val isReadOnly: Boolean,
    override val visibility: Expression,
    styling: Styling
) extends GUIQuestion(id, answerType, label, isReadOnly, visibility, component = None)

object QLSGUIQuestion {
  def apply(question: GUIQuestion) =
    new QLSGUIQuestion(
      question.id,
      question.answerType,
      question.label,
      question.isReadOnly,
      question.visibility,
      styling = DefaultStyle())
  def apply(question: GUIQuestion, styling: Styling) =
    new QLSGUIQuestion(
      question.id,
      question.answerType,
      question.label,
      question.isReadOnly,
      question.visibility,
      styling)
}

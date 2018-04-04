package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType
import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import nl.uva.se.sc.niro.ql.model.gui.Question

case class QLSQuestion(
    override val id: String,
    override val answerType: AnswerType,
    override val label: String,
    override val isReadOnly: Boolean,
    override val visibility: Expression,
    styling: Styling
) extends Question(id, answerType, label, isReadOnly, visibility, component = None)

object QLSQuestion {

  def apply(question: Question, styling: Styling) =
    new QLSQuestion(question.id, question.answerType, question.label, question.isReadOnly, question.visibility, styling)
}

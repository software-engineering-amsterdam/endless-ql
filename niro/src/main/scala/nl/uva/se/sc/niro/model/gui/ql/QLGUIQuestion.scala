package nl.uva.se.sc.niro.model.gui.ql

import nl.uva.se.sc.niro.model.ql.expressions.Expression
import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class QLGUIQuestion(
    override val id: String,
    override val answerType: AnswerType,
    override val label: String,
    override val isReadOnly: Boolean,
    override val visibility: Expression
) extends GUIQuestion(id, answerType, label, isReadOnly, visibility, component = None)

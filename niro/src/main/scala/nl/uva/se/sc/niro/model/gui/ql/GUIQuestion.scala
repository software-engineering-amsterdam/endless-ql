package nl.uva.se.sc.niro.model.gui.ql

import nl.uva.se.sc.niro.gui.component.Component
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


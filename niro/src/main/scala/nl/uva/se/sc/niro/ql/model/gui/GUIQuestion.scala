package nl.uva.se.sc.niro.ql.model.gui

import nl.uva.se.sc.niro.gui.component.Component
import nl.uva.se.sc.niro.ql.model.ast.AnswerType
import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression

abstract class GUIQuestion(
    val id: String,
    val answerType: AnswerType,
    val label: String,
    val isReadOnly: Boolean,
    val visibility: Expression,
    var component: Option[Component[_]]
)

package nl.uva.se.sc.niro.model.gui.ql

import nl.uva.se.sc.niro.gui.component.Component
import nl.uva.se.sc.niro.model.ql.expressions.Expression
import nl.uva.se.sc.niro.ql.model.ast.AnswerType

abstract class GUIQuestion(
    val id: String,
    val answerType: AnswerType,
    val label: String,
    val isReadOnly: Boolean,
    val visibility: Expression,
    var component: Option[Component[_]]
)

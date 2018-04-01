package nl.uva.se.sc.niro.ql.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType
import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import nl.uva.se.sc.niro.ql.view.component.Component

abstract class Question(
    val id: String,
    val answerType: AnswerType,
    val label: String,
    val isReadOnly: Boolean,
    val visibility: Expression,
    var component: Option[Component[_]]
)

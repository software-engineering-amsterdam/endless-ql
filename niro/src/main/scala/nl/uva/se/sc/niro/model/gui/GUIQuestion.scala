package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.{ AnswerType, Question }
import nl.uva.se.sc.niro.model.expressions.{ Answer, Expression }

case class GUIQuestion(
    id: String,
    answerType: AnswerType,
    label: String,
    isReadOnly: Boolean,
    isVisible: Expression // Holds the accumulated if-conditions.
)

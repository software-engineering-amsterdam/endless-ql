package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.expressions.Expression
import nl.uva.se.sc.niro.model.expressions.answers.Answer
import nl.uva.se.sc.niro.model.{ AnswerType, Question }

case class GUIQuestion(
    id: String,
    answerType: AnswerType,
    label: String,
    isReadOnly: Boolean,
    isVisible: Expression // Holds the accumulated if-conditions.
)

package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.{ AnswerType, Question }
import nl.uva.se.sc.niro.model.Expressions.{ Answer, Expression }

case class GUIQuestion(
    id: String,
    answerType: AnswerType,
    label: String,
    isReadOnly: Boolean,
    isVisible: Expression,
    // TODO Is this really needed?
    // Will probably end up in the symbol table... In which case it should be a simple lookup based on the id of the question
    var answer: Option[Answer] = None) {}

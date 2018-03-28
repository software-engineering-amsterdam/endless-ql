package nl.uva.se.sc.niro.model.gui.qls

import nl.uva.se.sc.niro.model.ql.AnswerType

case class GUISection(name: String, questions: Seq[GUIQuestionStyling], defaultStyles: Map[AnswerType, GUIStyling])

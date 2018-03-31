package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class GUIPage(name: String, sections: Seq[GUISection], defaultStyles: Map[AnswerType, GUIStyling])

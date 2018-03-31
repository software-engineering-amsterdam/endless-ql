package nl.uva.se.sc.niro.model.gui.qls

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class GUIPage(name: String, sections: Seq[GUISection], defaultStyles: Map[AnswerType, GUIStyling])

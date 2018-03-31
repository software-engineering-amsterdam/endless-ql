package nl.uva.se.sc.niro.model.gui.qls

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class GUISection(name: String, statements: Seq[GUIStatement], defaultStyles: Map[AnswerType, GUIStyling])
    extends GUIStatement

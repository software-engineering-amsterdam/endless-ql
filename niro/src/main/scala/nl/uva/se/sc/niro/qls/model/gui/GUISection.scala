package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class GUISection(name: String, statements: Seq[GUIStatement], defaultStyles: Map[AnswerType, GUIStyling])
    extends GUIStatement

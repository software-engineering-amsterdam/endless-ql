package nl.uva.se.sc.niro.model.gui.qls

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

/**
  * Model used by the frontend
  */
case class GUIStylesheet(name: String, pages: Seq[GUIPage], defaultStyles: Map[AnswerType, GUIStyling])

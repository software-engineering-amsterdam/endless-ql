package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

/**
  * Model used by the frontend
  */
case class GUIStylesheet(name: String, pages: Seq[GUIPage], defaultStyles: Map[AnswerType, GUIStyling])

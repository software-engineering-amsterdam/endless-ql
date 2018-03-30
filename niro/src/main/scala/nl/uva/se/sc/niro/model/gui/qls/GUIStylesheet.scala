package nl.uva.se.sc.niro.model.gui.qls

/**
  * Model used by the frontend
  */
import nl.uva.se.sc.niro.model.ql.AnswerType

case class GUIStylesheet(name: String, pages: Seq[GUIPage], defaultStyles: Map[AnswerType, GUIStyling])

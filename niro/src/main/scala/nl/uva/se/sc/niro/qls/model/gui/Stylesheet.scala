package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

/**
  * Model used by the frontend
  */
case class Stylesheet(name: String, pages: Seq[Page], defaultStyles: Map[AnswerType, Styling])

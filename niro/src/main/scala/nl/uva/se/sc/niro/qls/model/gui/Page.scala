package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class Page(name: String, sections: Seq[Section], defaultStyles: Map[AnswerType, Styling])

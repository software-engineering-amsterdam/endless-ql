package nl.uva.se.sc.niro.qls.model.gui

import nl.uva.se.sc.niro.ql.model.ast.AnswerType

case class Section(name: String, statements: Seq[Statement], defaultStyles: Map[AnswerType, Styling])
    extends Statement

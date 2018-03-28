package nl.uva.se.sc.niro.model.qls

import nl.uva.se.sc.niro.model.ql.AnswerType

case class Section(name: String, questions: Seq[Question], defaultStyles: Map[AnswerType, Styling])

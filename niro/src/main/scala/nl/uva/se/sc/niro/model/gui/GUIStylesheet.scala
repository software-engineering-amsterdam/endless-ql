package nl.uva.se.sc.niro.model.gui

case class GUIStylesheet(name: String, pages: Seq[GUIPage])

case class GUIPage(name: String, sections: Seq[GUISection])

case class GUISection(name: String, questions: Seq[GUIStyledQuestion])

case class GUIStyledQuestion(name: String, style: Styling)




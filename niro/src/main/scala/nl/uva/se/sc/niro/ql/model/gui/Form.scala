package nl.uva.se.sc.niro.ql.model.gui

case class Form(name: String, questions: Seq[Question]) {
  def collectQuestionOnName(name: String): Seq[Question] = questions.filter(_.id == name)
}

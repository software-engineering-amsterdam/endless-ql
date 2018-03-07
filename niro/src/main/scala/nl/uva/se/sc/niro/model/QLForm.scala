package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.expressions._

case class QLForm(formName: String, statements: Seq[Statement]) {
  val symbolTable: Map[String, Expression] =
    Statement.collectAllQuestions(statements).map(q => (q.id, q.expression)).toMap

  def save(questionId: String, answer: Answer): QLForm = {
    val updatedStatements = Statement.saveAnswer(questionId, answer, statements)
    QLForm(formName, updatedStatements)
  }
}

object QLForm {
  type SymbolTable = Map[String, Expression]
}

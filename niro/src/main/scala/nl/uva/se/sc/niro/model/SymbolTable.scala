package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.expressions.Expression
import nl.uva.se.sc.niro.model.expressions.answers.Answer

import scala.collection.mutable

/** This symbol table also does a double duty as memory space
* */
class SymbolTable(qLForm: QLForm) {
  val table: mutable.Map[String, Symbol] = {
    val declarationSymbolPair: Seq[(String, Symbol)] = Statement
      .collectAllQuestions(qLForm.statements)
      .map(q => q.id -> Symbol(q.answerType, q.expression, q.answer))
    mutable.Map(declarationSymbolPair: _*)
  }

  def save(questionId: String, answer: Answer): Unit = {
    val symbol = table(questionId)
    table(questionId) = symbol.copy(answer = Some(answer))
  }
}

case class Symbol(answerType: AnswerType, expression: Expression, answer: Option[Answer])
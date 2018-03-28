package nl.uva.se.sc.niro.model.ql.evaluation

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.evaluation.ExpressionEvaluator._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer

object QLFormEvaluator {

  type Dictionary = Map[String, Answer]

  def evaluate(qLForm: QLForm, dictionary: Dictionary): Dictionary = {
    qLForm.symbolTable
      .map {
        case (questionId, symbol) =>
          val optionalExpression: Option[Expression] = symbol.expression.orElse(dictionary.get(questionId))

          val optionalAnswer: Option[Answer] = optionalExpression.flatMap(_.evaluate(qLForm.symbolTable, dictionary))

          questionId -> optionalAnswer
      }
      .collect { case (questionId, Some(answer)) => (questionId, answer) }
  }
}

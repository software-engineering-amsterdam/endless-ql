package nl.uva.se.sc.niro.ql.model.ast.evaluation

import nl.uva.se.sc.niro.ql.model.ast.evaluation.ExpressionEvaluator._
import nl.uva.se.sc.niro.ql.model.ast.expressions._
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.Answer

object QLFormEvaluator {

  type ValueStore = Map[String, Answer]

  def evaluate(qLForm: QLForm, dictionary: ValueStore): ValueStore = {
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

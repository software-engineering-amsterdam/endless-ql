package nl.uva.se.sc.niro.typechecking.ql

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql._
import org.apache.logging.log4j.scala.Logging
import nl.uva.se.sc.niro.model.ql.evaluation.ExpressionEvaluator._

object PredicateChecker extends Logging {
  def checkNonBooleanPredicates(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 3 - Checking predicates that are not of the type boolean ...")

    val conditionals: Seq[Conditional] = Statement.collectAllConditionals(qLForm.statements)
    val conditionalsWithNonBooleanPredicates: Seq[Conditional] = conditionals filter { conditional =>
      conditional.predicate.evaluate(qLForm.symbolTable, Map.empty) != BooleanType.asRight
    }

    if (conditionalsWithNonBooleanPredicates.nonEmpty) {
      Left(TypeCheckError(message = s"Non boolean predicate: $conditionalsWithNonBooleanPredicates"))
    } else {
      Right(qLForm)
    }
  }
}

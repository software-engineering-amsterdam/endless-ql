package nl.uva.se.sc.niro.typechecking.ql

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.typechecking.ql.StaticTypeChecker._
import org.apache.logging.log4j.scala.Logging
import nl.uva.se.sc.niro.PrettyPrinter.ExpressionCanPrettyPrint

object PredicateChecker extends Logging {
  def checkNonBooleanPredicates(qlForm: QLForm): Either[Seq[TypeCheckError], QLForm] = {
    logger.info("Phase 3 - Checking predicates that are not of the type boolean ...")

    val conditionals: Seq[Conditional] = Statement.collectAllConditionals(qlForm.statements)
    val conditionalsWithNonBooleanPredicates: Seq[Conditional] = conditionals filterNot { conditional =>
      conditional.predicate.typeOf(qlForm.symbolTable).contains(BooleanType)
    }

    if (conditionalsWithNonBooleanPredicates.nonEmpty) {
      conditionalsWithNonBooleanPredicates
        .map(nonBooleanPredicate => TypeCheckError(message = s"Non boolean predicate: ${nonBooleanPredicate.predicate.prettyPrint}"))
      .asLeft
    } else {
      qlForm.asRight
    }
  }
}

package nl.uva.se.sc.niro.typechecking.ql

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.typechecking.ql.CyclicDependencyChecker.checkCyclicDependenciesBetweenQuestions
import nl.uva.se.sc.niro.typechecking.ql.DuplicateQuestionChecker.{
  checkDuplicateLabels,
  checkDuplicateQuestionDeclarationsWithDifferentTypes
}
import nl.uva.se.sc.niro.typechecking.ql.PredicateChecker.checkNonBooleanPredicates
import nl.uva.se.sc.niro.typechecking.ql.ReferenceChecker.checkReferences
import nl.uva.se.sc.niro.typechecking.ql.StaticTypeChecker.checkOperandsOfInvalidTypeToOperators
import org.apache.logging.log4j.scala.Logging

object TypeCheckerFacade extends Logging {

  /** Performs all type checking phases. Early aborts when one of the tasks returns a TypeCheckError
    * */
  // Order of execution is important here to avoid infinite loops in subsequent phases
  def pipeline(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] =
    for {
      _ <- checkReferences(qLForm)
      _ <- checkCyclicDependenciesBetweenQuestions(qLForm)
      _ <- checkOperandsOfInvalidTypeToOperators(qLForm)
      _ <- checkNonBooleanPredicates(qLForm)
      _ <- checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm)
      qlFormWithPossibleWarnings = checkDuplicateLabels(qLForm)
    } yield qlFormWithPossibleWarnings
}

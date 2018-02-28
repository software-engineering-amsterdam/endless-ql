package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.Expressions.Reference
import nl.uva.se.sc.niro.model.Expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model.{ QLForm, Question, Statement }
import org.apache.logging.log4j.scala.Logging

object TypeChecker extends Logging {

  def pipeline: QLForm => QLForm =
    checkOperandsOfInvalidTypeToOperators _ andThen
      checkReferences andThen
      checkNonBooleanPredicates andThen
      checkDuplicateQuestionDeclarationsWithDifferentTypes andThen
      checkCyclicDependenciesBetweenQuestions andThen
      checkDuplicateLabels

  def checkOperandsOfInvalidTypeToOperators(qLForm: QLForm): QLForm = {
    logger.debug("Checking on operands of invalid type to operators ...")

    qLForm
  }

  // TODO This function does not check for references inside expressions
  def checkReferences(qLForm: QLForm): QLForm = {
    logger.debug("Checking on references to undefined questions ...")
    val questions = Statement.collectAllQuestions(qLForm.statements)
    val references: Seq[Reference] = questions.collect { case Question(_, _, _, r @ Reference(_), _) => r }
    val undefinedReferences = references.map(_.value).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      throw new IllegalArgumentException(s"Undefined references $undefinedReferences")
    }

    qLForm
  }

  def checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm: QLForm): QLForm = {
    logger.debug("Checking on duplicate question declarations with different types ...")

    qLForm
  }

  // TODO get rid of is instance of
  def checkNonBooleanPredicates(qLForm: QLForm): QLForm = {
    logger.debug("Checking on predicates that are not of the type boolean ...")
    val conditionals = Statement.collectAllConditionals(qLForm.statements)
    val nonBooleanPredicates = conditionals.collect {
      case c if !Evaluator.evaluateExpression(c.predicate, qLForm.symbolTable).isInstanceOf[BooleanAnswer] => c
    }

    if (nonBooleanPredicates.nonEmpty) {
      throw new IllegalArgumentException(s"Undefined references $nonBooleanPredicates")
    }

    qLForm
  }

  def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): QLForm = {
    logger.debug("Checking on cyclic dependencies between questions ...")

    qLForm
  }

  // TODO this function should not throw an error. Somehow we should give a warning when duplicate labels are detected
  def checkDuplicateLabels(qLForm: QLForm): QLForm = {
    logger.debug("Checking on duplicate question labels ...")
    val questions = Statement.collectAllQuestions(qLForm.statements)
    val questionsWithDuplicateLabels: Iterator[Seq[Question]] =
      questions.groupBy(_.label).valuesIterator.filter { _.size > 1 }

    if (questionsWithDuplicateLabels.nonEmpty) {
      throw new IllegalArgumentException(s"Found questions with duplicate labels $questionsWithDuplicateLabels")
    }
    qLForm
  }
}

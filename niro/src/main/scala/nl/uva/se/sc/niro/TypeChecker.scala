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

  // TODO implement checkOperandsOfInvalidTypeToOperators
  def checkOperandsOfInvalidTypeToOperators(qLForm: QLForm): QLForm = {
    logger.info("Phase 1 - Checking operands of invalid type to operators ...")
    qLForm
  }

  def checkReferences(qLForm: QLForm): QLForm = {
    logger.info("Phase 2 - Checking references to undefined questions ...")
    val questions = Statement.collectAllQuestions(qLForm.statements)
    val references: Seq[Reference] = Statement.collectAllReferences(questions)
    val undefinedReferences = references.map(_.value).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      throw new IllegalArgumentException(s"Undefined references $undefinedReferences")
    }

    qLForm
  }

  def checkNonBooleanPredicates(qLForm: QLForm): QLForm = {
    logger.info("Phase 3 - Checking predicates that are not of the type boolean ...")
    val conditionals = Statement.collectAllConditionals(qLForm.statements)
    val conditionalsWithNonBooleanPredicates = conditionals filter { conditional =>
      Evaluator.evaluateExpression(conditional.predicate, qLForm.symbolTable) match {
        case _: BooleanAnswer => false
        case _                => true
      }
    }

    if (conditionalsWithNonBooleanPredicates.nonEmpty) {
      throw new IllegalArgumentException(s"Undefined references $conditionalsWithNonBooleanPredicates")
    }

    qLForm
  }

  def checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm: QLForm): QLForm = {
    logger.info("Phase 4 - Checking duplicate question declarations with different types ...")
    val questions = Statement.collectAllQuestions(qLForm.statements)
    val duplicateQuestions = questions.groupBy(_.id).valuesIterator.filter(_.size > 1)

    val duplicateQuestionsWithDifferentTypes = duplicateQuestions.filter(questionsWithMultipleOccurrences =>
      questionsWithMultipleOccurrences.map(_.answerType).toSet.size > 1)

    if (duplicateQuestionsWithDifferentTypes.nonEmpty) {
      throw new IllegalArgumentException(s"Undefined references $duplicateQuestionsWithDifferentTypes")
    }

    qLForm
  }

  // TODO implement checkCyclicDependenciesBetweenQuestions
  def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): QLForm = {
    logger.info("Phase 5 - Checking cyclic dependencies between questions ...")
    val questions = Statement.collectAllQuestions(qLForm.statements)
    val dependencyGraph: Seq[(String, String)] = questions.flatMap {
      case q @ Question(_, _, _, r @ Reference(_), _) => Seq(q.id -> r.value)
      case q @ Question(_, _, _, expression, _)       => Statement.collectAllReferences(expression).map(r => q.id -> r.value)
    }

    val cyclicDependencies = dependencyGraph.exists(element => detectCycle(element, dependencyGraph, element))
    if (cyclicDependencies) {
      throw new IllegalArgumentException(s"Found cyclic dependency")
    }

    qLForm
  }

  private def detectCycle(
      follow: (String, String),
      graph: Seq[(String, String)],
      startingElement: (String, String)): Boolean = {
    logger.info(s"Detecting cycles for starting element: $startingElement in graph: $graph. Now following: $follow")

    val nextElement: Option[(String, String)] = graph.find(_._1 == follow._2)
    nextElement match {
      case Some((from, to)) if startingElement._1 == to =>
        logger.info(s"Detected a cycle between: $startingElement -> ${(from, to)}")
        true
      case Some((from, to)) => detectCycle((from, to), graph.filterNot(pair => pair == follow), startingElement)
      case None             => false
    }
  }

  // TODO this function should not throw an error. Somehow we should give a warning when duplicate labels are detected
  def checkDuplicateLabels(qLForm: QLForm): QLForm = {
    logger.info("Phase 6 - Checking duplicate question labels ...")

    val questions = Statement.collectAllQuestions(qLForm.statements)
    val questionsWithDuplicateLabels: Iterator[Seq[Question]] =
      questions.groupBy(_.label).valuesIterator.filter(_.size > 1)

    if (questionsWithDuplicateLabels.nonEmpty) {
      throw new IllegalArgumentException(s"Found questions with duplicate labels $questionsWithDuplicateLabels")
    }

    qLForm
  }
}

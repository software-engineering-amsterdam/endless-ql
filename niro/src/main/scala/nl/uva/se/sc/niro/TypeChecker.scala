package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.Expressions.Reference
import nl.uva.se.sc.niro.model.Expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model.{ Conditional, QLForm, Question, Statement }
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

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val references: Seq[Reference] = Statement.collectAllReferences(questions)
    val undefinedReferences: Seq[String] = references.map(_.value).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      throw new IllegalArgumentException(s"Undefined references $undefinedReferences")
    }

    qLForm
  }

  def checkNonBooleanPredicates(qLForm: QLForm): QLForm = {
    logger.info("Phase 3 - Checking predicates that are not of the type boolean ...")

    val conditionals: Seq[Conditional] = Statement.collectAllConditionals(qLForm.statements)
    val conditionalsWithNonBooleanPredicates: Seq[Conditional] = conditionals filter { conditional =>
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

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val duplicateQuestions: Iterator[Seq[Question]] = questions.groupBy(_.id).valuesIterator.filter(_.size > 1)

    val duplicateQuestionsWithDifferentTypes: Iterator[Seq[Question]] = duplicateQuestions.filter(
      questionsWithMultipleOccurrences => questionsWithMultipleOccurrences.map(_.answerType).toSet.size > 1)

    if (duplicateQuestionsWithDifferentTypes.nonEmpty) {
      throw new IllegalArgumentException(s"Undefined references $duplicateQuestionsWithDifferentTypes")
    }

    qLForm
  }

  def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): QLForm = {
    logger.info("Phase 5 - Checking cyclic dependencies between questions ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)

    val dependencyGraph: Graph = buildDependencyGraph(questions)

    val cyclicDependencies: Boolean =
      dependencyGraph.exists(element => detectCycle(element, dependencyGraph, Seq(element)))
    if (cyclicDependencies) {
      throw new IllegalArgumentException(s"Found cyclic dependency")
    }

    qLForm
  }

  type Edge = (String, String)
  type Graph = Seq[Edge]

  private def buildDependencyGraph(questions: Seq[Question]): Graph = {
    questions.flatMap {
      case q @ Question(_, _, _, r @ Reference(_), _) => Seq(q.id -> r.value)
      case q @ Question(_, _, _, expression, _)       => Statement.collectAllReferences(expression).map(r => q.id -> r.value)
    }
  }

  private def detectCycle(currentEdge: Edge, graph: Graph, followedPath: Graph): Boolean = {
    logger.info(
      s"Detecting cycles for starting element: ${followedPath.head} in graph: $graph. Now following: $currentEdge")

    val connectedEdges: Seq[Edge] = graph.filter(_._1 == currentEdge._2)

    if (connectedEdges.isEmpty) {
      logger.info(s"No cycle detected in ${followedPath.tail :+ currentEdge} for element ${followedPath.head}")
      false
    } else {
      connectedEdges.exists { connectedEdge =>
        if (followedPath.head._1 == connectedEdge._2) {
          val completePath = ((followedPath.tail :+ currentEdge).map(_._1) :+ connectedEdge._2).mkString(" -> ")
          logger.info(s"Detected a cycle: $completePath")
          true
        } else {
          detectCycle(connectedEdge, graph, followedPath :+ currentEdge)
        }
      }
    }
  }

  // TODO this function should not throw an error. Somehow we should give a warning when duplicate labels are detected
  def checkDuplicateLabels(qLForm: QLForm): QLForm = {
    logger.info("Phase 6 - Checking duplicate question labels ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val questionsWithDuplicateLabels: Iterator[Seq[Question]] =
      questions.groupBy(_.label).valuesIterator.filter(_.size > 1)

    if (questionsWithDuplicateLabels.nonEmpty) {
      throw new IllegalArgumentException(s"Found questions with duplicate labels $questionsWithDuplicateLabels")
    }

    qLForm
  }
}

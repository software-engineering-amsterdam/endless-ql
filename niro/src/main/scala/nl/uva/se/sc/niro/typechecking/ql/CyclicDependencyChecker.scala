package nl.uva.se.sc.niro.typechecking.ql

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.expressions.{ Expression, Reference }
import nl.uva.se.sc.niro.model.ql.{ QLForm, Question, Statement }
import nl.uva.se.sc.niro.typechecking.ql.CycleDetection.{ Edge, Graph, detectCycles, graphToString }
import org.apache.logging.log4j.scala.Logging

object CyclicDependencyChecker extends Logging {
  def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 2 - Checking cyclic dependencies between questions ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)

    val dependencyGraph: Graph = buildDependencyGraph(questions)

    val cyclicDependencies: Seq[Graph] =
      dependencyGraph.flatMap(element => detectCycles(dependencyGraph, Seq(element)))

    if (cyclicDependencies.nonEmpty) {
      Left(TypeCheckError(message = s"Found cyclic dependencies: ${cyclicDependencies.map(graphToString)}"))
    } else {
      Right(qLForm)
    }
  }

  private def buildDependencyGraph(questions: Seq[Question]): Graph = {
    questions.collect {
      case q @ Question(_, _, _, Some(r @ Reference(_))) => Seq(Edge(q.id, r.questionId))
      case q @ Question(_, _, _, Some(expression)) =>
        Expression.collectAllReferences(expression).map(r => Edge(q.id, r.questionId))
    }.flatten
  }
}

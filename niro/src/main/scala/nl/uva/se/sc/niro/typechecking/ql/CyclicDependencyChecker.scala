package nl.uva.se.sc.niro.typechecking.ql

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions.Expression
import nl.uva.se.sc.niro.model.ql.{ QLForm, Symbol }
import nl.uva.se.sc.niro.typechecking.ql.CycleDetection.{ Edge, Graph, detectCycles, graphToString }
import org.apache.logging.log4j.scala.Logging

object CyclicDependencyChecker extends Logging {
  def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 2 - Checking cyclic dependencies between questions ...")

    val dependencyGraph: Graph = buildDependencyGraph(qLForm.symbolTable)

    val cyclicDependencies: Seq[Graph] =
      dependencyGraph.flatMap(element => detectCycles(dependencyGraph, Seq(element)))

    if (cyclicDependencies.nonEmpty) {
      Left(TypeCheckError(message = s"Found cyclic dependencies: ${cyclicDependencies.map(graphToString)}"))
    } else {
      Right(qLForm)
    }
  }

  private def buildDependencyGraph(symbolTable: SymbolTable): Graph = {
    symbolTable.flatMap {
      case (questionId, Symbol(_, Some(expression))) =>
        Expression.collectAllReferences(expression).map(r => Edge(questionId, r.questionId))
    }.toSeq
  }
}

package nl.uva.se.sc.niro.ql.parser.typecheck

import cats.implicits._
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.ql.model.ast.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import nl.uva.se.sc.niro.ql.model.ast.{ QLForm, Symbol }
import nl.uva.se.sc.niro.ql.parser.typecheck.CycleDetection.{ Edge, Graph, detectCycles }
import nl.uva.se.sc.niro.util.PrettyPrinter.GraphCanPrettyPrint
import org.apache.logging.log4j.scala.Logging

object CyclicDependency extends Logging {
  def check(qLForm: QLForm): Either[Seq[TypeCheckError], QLForm] = {
    logger.info("Phase 2 - Checking cyclic dependencies between questions ...")

    val dependencyGraph: Graph = buildDependencyGraph(qLForm.symbolTable)

    val cyclicDependencies: Seq[Seq[Graph]] =
      dependencyGraph.map(element => detectCycles(dependencyGraph, Seq(element))).filter(_.nonEmpty)

    if (cyclicDependencies.nonEmpty) {
      cyclicDependencies
        .map(cycle => TypeCheckError(message = s"Found cyclic dependency: ${cycle.map(_.prettyPrint).mkString("")}"))
        .asLeft
    } else {
      qLForm.asRight
    }
  }

  private def buildDependencyGraph(symbolTable: SymbolTable): Graph = {
    symbolTable.flatMap {
      case (questionId, Symbol(_, Some(expression))) =>
        Expression.collectAllReferences(expression).map(r => Edge(questionId, r.questionId))
      case _ => Seq.empty
    }.toSeq
  }
}

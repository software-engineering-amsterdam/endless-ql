package nl.uva.se.sc.niro.typechecking.ql

import nl.uva.se.sc.niro.PrettyPrinter.GraphCanPrettyPrint
import org.apache.logging.log4j.scala.Logging

object CycleDetection extends Logging {

  case class Edge(from: String, to: String)
  type Graph = Seq[Edge]

  /** Returns a sequence of detected cycles or an empty sequence if no cycles are found.
    * */
  def detectCycles(graph: Graph, followedPath: Graph): Seq[Graph] = {
    logger.info(s"Detecting cycles in graph: $graph. Now traversing: $followedPath")

    val currentEdge = followedPath.last
    val connectedEdges: Seq[Edge] = graph.filter { _.from == currentEdge.to }

    if (connectedEdges.isEmpty) {
      logger.info(s"No cycle detected in $followedPath")
      Seq.empty
    } else {
      connectedEdges.flatMap { connectedEdge =>
        val currentPath = followedPath :+ connectedEdge
        if (followedPath.head.from == connectedEdge.to) {
          val completePath = currentPath.prettyPrint
          logger.info(s"Detected a cycle: $completePath")
          Seq(followedPath :+ connectedEdge)
        } else {
          detectCycles(graph, currentPath)
        }
      }
    }
  }

}

package nl.uva.se.sc.niro.typechecking

import org.apache.logging.log4j.scala.Logging

object CycleDetection extends Logging {

  case class Edge(from: String, to: String)
  type Graph = Seq[Edge]

  def graphToString(followedPath: Graph): String = {
    (followedPath.init.map(_.from) :+ followedPath.last.from :+ followedPath.last.to).mkString(" -> ")
  }

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
          val completePath = graphToString(currentPath)
          logger.info(s"Detected a cycle: $completePath")
          Seq(followedPath :+ connectedEdge)
        } else {
          detectCycles(graph, currentPath)
        }
      }
    }
  }
}

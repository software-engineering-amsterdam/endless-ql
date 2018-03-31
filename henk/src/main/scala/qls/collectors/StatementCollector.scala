package qls.collectors

import qls.models.ast._

import scala.collection.JavaConversions._

object StatementCollector {
  def getPages(node: Statement): List[Page] = {
    flattenStatements(node).collect { case p: Page => p }
  }

  def flattenStatements(node: Statement): List[Statement] = {
    node match {
      case root: Root         => List(root) ++ flattenStatements(root.body)
      case body: RootBody =>
        List(body) ++ body.pages.flatMap(flattenStatements(_))
      case page: Page => List(page)
    }
  }
}

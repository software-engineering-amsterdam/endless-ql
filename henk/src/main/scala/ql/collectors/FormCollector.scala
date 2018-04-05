package ql.collectors

import ql.models.ast._
import ql.parsers._

object FormCollector {
  def getStatements(node: FormElement): List[Statement] = {
    flattenForm(node).collect { case body: Body => body.statements }.flatten
  }

  def getExpressions(node: FormElement): List[Expression] = {
    flattenForm(node).collect { case header: Header => header.identifier }
  }

  def flattenForm(node: FormElement): List[FormElement] = {
    node match {
      case root: Root => List(root) ++ flattenForm(root.body) ++ flattenForm(root.header)
      case header: Header => List(header)
      case body: Body => List(body)
    }
  }
}

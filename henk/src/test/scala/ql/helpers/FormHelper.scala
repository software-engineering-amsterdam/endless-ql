package ql.spec.helpers

import ql.models.ast._
import ql.parsers._
import ql.collectors._

import scala.io.Source
import java.net.URL

object FormHelper {
  def getQuestions(location: URL): List[Question] = {
    val root = QLParser.getRoot(location)
    FormCollector.getStatements(root).flatMap(StatementCollector.getQuestions)
  }

  def getIfStatements(location: URL): List[IfStatement] = {
    val root = QLParser.getRoot(location)
    FormCollector.getStatements(root).flatMap(StatementCollector.getIfStatements)
  }

  def getElseStatements(location: URL): List[ElseStatement] = {
    val root = QLParser.getRoot(location)
    FormCollector.getStatements(root).flatMap(StatementCollector.getElseStatements)
  }

  def getExpressions(location: URL): List[Expression] = {
    val root = QLParser.getRoot(location)
    FormCollector.getStatements(root).map(StatementCollector.getExpressions).flatten ++
    FormCollector.getExpressions(root)
  }

  def getRoot(location: URL): Root = {
    QLParser.getRoot(location)
  }
}

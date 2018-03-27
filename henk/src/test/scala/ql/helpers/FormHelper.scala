package ql.spec.helpers

import ql.models.ast._
import ql.parsers._
import ql.collectors._

import scala.io.Source
import java.net.URL

object FormHelper {
  def getQuestions(location: URL): List[Question] = {
    val form = QLParser.getForm(location)
    StatementCollector.getQuestions(form)
  }

  def getIfStatements(location: URL): List[IfStatement] = {
    val form = QLParser.getForm(location)
    StatementCollector.getIfStatements(form)
  }

  def getExpressions(location: URL): List[Expression] = {
    val form = QLParser.getForm(location)
    ExpressionCollector.getExpressions(form)
  }

  def getRoot(location: URL): Statement = {
    QLParser.getForm(location)
  }
}

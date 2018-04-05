package qls.spec.helpers

import ql.models.ast.{ Identifier }

import qls.models.ast._
import qls.parsers._
import qls.collectors._

import scala.io.Source
import java.net.URL

object FormHelper {
  val parser = QLSParser

  def getRoot(location: URL): Root = {
    parser.getRoot(location)
  }

  def getPages(location: URL): List[Page] = {
    val form = parser.getRoot(location)
    StatementCollector.getPages(form) 
  }

  def getSections(location: URL): List[Section] = {
    val form = parser.getRoot(location)
    ElementCollector.getSections(form) 
  }

  def getDefaultDecls(location: URL): List[DefaultDecl] = {
    val form = parser.getRoot(location)
    ElementCollector.getDefaultDecls(form) 
  }

  def getQuestion(location: URL, identifier: Identifier): Option[Question] = {
    val form = parser.getRoot(location)
    ElementCollector.getQuestion(form, identifier) 
  }

  def getQuestions(location: URL): List[Question] = {
    val form = parser.getRoot(location)
    ElementCollector.getQuestions(form) 
  }
}

package qls.spec.helpers

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
    val form = parser.getForm(location)
    StatementCollector.getPages(form) 
  }

  def getSections(location: URL): List[Section] = {
    val form = parser.getForm(location)
    ElementCollector.getSections(form) 
  }

  def getDefaultDecls(location: URL): List[DefaultDecl] = {
    val form = parser.getForm(location)
    ElementCollector.getDefaultDecls(form) 
  }

  def getQuestion(location: URL, identifier: Identifier): Option[Question] = {
    val form = parser.getForm(location)
    ElementCollector.getQuestion(form, identifier) 
  }

  def getQuestions(location: URL): List[Question] = {
    val form = parser.getForm(location)
    ElementCollector.getQuestions(form) 
  }
}

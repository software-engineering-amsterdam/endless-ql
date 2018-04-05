package qls.collectors

import qls.models.ast._
import ql.models.ast.{ Identifier }

import scala.collection.JavaConversions._

object ElementCollector {
  def getElements(node: Statement): List[DisplayItem] = {
    StatementCollector.getDisplayItems(node).flatMap(flattenElements)
  }

  def getSections(node: Statement): List[Section] = {
    getElements(node).collect {
      case s: Section => s
    }
  }

  def getDefaultDecls(node: Statement): List[DefaultDecl] = {
    getElements(node).collect {
      case dd: DefaultDecl => dd
    }
  }

  def getQuestions(node: Statement): List[Question] = {
    getElements(node).collect {
      case q: Question => q
    }
  }

  def getQuestion(node: Statement, identifier: Identifier): Option[Question] = {
    val questions = getQuestions(node)
    questions.find(q => q.identifier == identifier)
  }

  def flattenElements(node: DisplayItem): List[DisplayItem] = {
    node match {
      case s: Section => List(s) ++ s.content.flatMap(flattenElements(_))
      case q: Question => List(q)
      case dd: DefaultDecl => List(dd)
    }
  }
}

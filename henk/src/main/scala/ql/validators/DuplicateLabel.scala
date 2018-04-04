package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.collectors._

import scala.collection.JavaConversions._

case class DuplicateLabelDeclaration(label: String) extends Exception(label)

class DuplicateLabelValidator extends WarningValidator {
  var warnings: Option[List[String]] = None

  def execute(ast: Statement): Boolean = {
    val labels = StatementCollector.getQuestions(ast).map(_.label)
    
    warnings = getDuplicateLabels(labels)
      .map(toPrettyLabels)

    warnings.isEmpty
  }

  private def toPrettyLabels(labels: List[String]): List[String] = {
    labels.map(label => s"Label '${label} is duplicate'")
  }

  def getDuplicateLabels(labels: List[String]): Option[List[String]] = {
    Option(labels.diff(labels.distinct)).filter(!_.isEmpty)
  }

  override def getWarnings(): Option[List[String]] = {
    warnings
  }
}

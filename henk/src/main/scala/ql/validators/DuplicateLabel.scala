package ql.validators

import ql.models.ast._
import ql.parsers._
import ql.collectors._

import scala.collection.JavaConversions._

case class DuplicateLabelDeclaration(label: String) extends Exception(label)

class DuplicateLabelValidator extends BaseValidator {
  var warnings: List[String] = List()

  def check(ast: Statement): Option[Exception] = {
    val labels = StatementCollector.getQuestions(ast).map(_.label)
    val distinctedLabels = labels.distinct
    if(labels != distinctedLabels) {

      warnings = labels.diff(distinctedLabels).map{ duplicateLabel => {
        s"Label '${duplicateLabel} is duplicate'"
      }}
      Some(new DuplicateLabelDeclaration("duplicate label detected"))
    } else {
      None
    }
  }

  override def getWarnings(): Option[List[String]] = {
    Some(warnings)
  }
}

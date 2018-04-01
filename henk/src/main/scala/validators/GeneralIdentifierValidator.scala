package general.validators

import ql.models.ast.{Statement => QLStatement}
import qls.models.ast.{Statement => QLSStatement}

import ql.collectors.{
  StatementCollector => StatementCollectorQL,
  ExpressionCollector
}

import qls.collectors.{ElementCollector => ElementCollectorQLS}

import scala.collection.JavaConversions._

case class UndeclaredQuestionStyling(label: String) extends Exception(label)

class GeneralIdentifierValidator extends BaseValidator {
  def check(ql: QLStatement, qls: QLSStatement): Option[Exception] = {
    val QLIdentifiers = getQLIdentifiers(ql)
    val QLSIdentifiers = getQLSIdentifier(qls)

    QLSIdentifiers
      .diff(QLIdentifiers)
      .map(undecl => {
        val message = s"Question '${undecl}' appears in QLS but not in QL"
        return Some(new UndeclaredQuestionStyling(message))
      })
    None
  }

  def getQLIdentifiers(ql: QLStatement): List[String] = {
    val questions = StatementCollectorQL.getQuestions(ql)
    val vardecls = questions.flatMap(ExpressionCollector.getIdentifiers)
    vardecls.map(_.id)
  }

  def getQLSIdentifier(qls: QLSStatement): List[String] = {
    val questions = ElementCollectorQLS.getQuestions(qls)
    questions.map(_.identifier.id)
  }
}

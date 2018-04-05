package general.validators

import ql.models.ast.{Root => QLRoot}
import qls.models.ast.{Statement => QLSStatement}

import ql.collectors.{
  StatementCollector => StatementCollectorQL,
  ExpressionCollector,
  FormCollector
}

import qls.collectors.{ElementCollector => ElementCollectorQLS}

import scala.collection.JavaConversions._

case class UnplacedQuestion(label: String) extends Exception(label)

class GeneralQuestionPlacementValidator extends BaseValidator {
  def execute(ql: QLRoot, qls: QLSStatement): Unit = {
    val QLIdentifiers = getQLIdentifiers(ql)
    val QLSIdentifiers = getQLSIdentifier(qls)

    QLIdentifiers
      .diff(QLSIdentifiers)
      .map(undecl => {
        val message = s"Question '${undecl}' is declared in QL but not placed in QLS"
        throw new UnplacedQuestion(message)
      })
  }

  def getQLIdentifiers(ql: QLRoot): List[String] = {
    val questions = FormCollector.getStatements(ql).flatMap(StatementCollectorQL.getQuestions)
    val vardecls = questions.flatMap(ExpressionCollector.getIdentifiers)
    vardecls.map(_.id)
  }

  def getQLSIdentifier(qls: QLSStatement): List[String] = {
    val questions = ElementCollectorQLS.getQuestions(qls)
    questions.map(_.identifier.id)
  }
}

package general.validators

import ql.models.ast.{Root => QLRoot, NodeType, Identifier }
import qls.models.ast.{
  Statement => QLSStatement,
  Styling,
  WidgetExpression
}

import ql.collectors.{
  StatementCollector => StatementCollectorQL,
  ExpressionCollector,
  TypeCollector,
  FormCollector
}

import qls.collectors.{
  ElementCollector => ElementCollectorQLS,
  StylingCollector
}

import scala.collection.JavaConversions._

case class IncompatibleTypes(label: String) extends Exception(label)

class GeneralTypeCheckerValidator extends BaseValidator {
  def execute(ql: QLRoot, qls: QLSStatement): Unit = {
    val statements = FormCollector.getStatements(ql)
    val qql = statements.flatMap(StatementCollectorQL.getQuestions)

    qql.map(question => {
      val varDeclQL = question.varDecl
      val questionQLS =
        ElementCollectorQLS.getQuestion(qls, varDeclQL.id).get

      questionQLS.styling.map(x => {
        getWidgetStyling(x).map(widgetExpression => {
          if (!widgetExpression.canHold(varDeclQL.typeDecl)) {
            val message =
              s"Widget '${widgetExpression}' cannot hold '${varDeclQL.typeDecl}'"
            throw new IncompatibleTypes(message)
          }
        })

        getWidgetType(x).map(widgetType => {
          if (widgetType != varDeclQL.typeDecl) {
            val message =
              s"Type mismatch: QL has '${varDeclQL.typeDecl}' declared while QLS has '${widgetType}'"
            throw new IncompatibleTypes(message)
          }
        })
      })
    })
  }

  def getWidgetStyling(style: Styling): Option[WidgetExpression] = {
    StylingCollector
      .getWidgetStyling(style)
      .map(_.value)
  }

  def getWidgetType(style: Styling): Option[NodeType] = {
    StylingCollector
      .getWidgetStyling(style)
      .map(_.value)
      .flatMap(_.widgetType)
  }
}

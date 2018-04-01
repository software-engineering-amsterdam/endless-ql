package general.validators

import ql.models.ast.{Statement => QLStatement, NodeType}
import qls.models.ast.{
  Statement => QLSStatement,
  Identifier,
  Styling,
  WidgetExpression
}

import ql.collectors.{
  StatementCollector => StatementCollectorQL,
  ExpressionCollector,
  TypeCollector
}

import qls.collectors.{
  ElementCollector => ElementCollectorQLS,
  StylingCollector
}

import scala.collection.JavaConversions._

case class IncompatibleTypes(label: String) extends Exception(label)

object GeneralTypeCheckerValidator {
  def check(ql: QLStatement, qls: QLSStatement): Option[IncompatibleTypes] = {
    val qql = StatementCollectorQL.getQuestions(ql)

    qql.map(question => {
      val varDeclQL = question.varDecl
      val questionQLS =
        ElementCollectorQLS.getQuestion(qls, Identifier(varDeclQL.id.id)).get

      questionQLS.styling.map(x => {
        getWidgetStyling(x).map(widgetExpression => {
          if (!widgetExpression.canHold(varDeclQL.typeDecl)) {
            val message =
              s"Widget '${widgetExpression}' cannot hold '${varDeclQL.typeDecl}'"
            return Some(new IncompatibleTypes(message))
          }
        })

        getWidgetType(x).map(widgetType => {
          if (widgetType != varDeclQL.typeDecl) {
            val message =
              s"Type mismatch: QL has '${varDeclQL.typeDecl}' declared while QLS has '${widgetType}'"
            return Some(new IncompatibleTypes(message))
          }
        })
      })
    })
    None
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

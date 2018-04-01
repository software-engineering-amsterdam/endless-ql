package qls.validators

import qls.models.ast._
import ql.models.ast.{ NodeType }
import qls.collectors._

import scala.collection.JavaConversions._

case class IncompatibleWidgetType(label: String) extends Exception(label)

object WidgetTypeCheckerValidator {
  def check(qls: Statement): Option[Exception] = {
    val defaultDecls = ElementCollector.getDefaultDecls(qls)

    defaultDecls.map(decl => {
      getWidgetStyling(decl.styling).map(widgetExpression => {
        if(!widgetExpression.canHold(decl.nodeType)) {
          val message = s"Widget '${widgetExpression}' cannot hold '${decl.nodeType}'"
          return Some(new IncompatibleWidgetType(message))
        }
      })

      getWidgetType(decl.styling).map(widgetType => {
        if(widgetType != decl.nodeType) {
          val message = s"Type of defaultdecl '${decl.nodeType}' and widget type are not compatible"
          return Some(new IncompatibleWidgetType(message))
        }
      })
    })
    None
  }

  def getWidgetStyling(style: Styling): Option[WidgetExpression] = {
    StylingCollector.getWidgetStyling(style)
    .map(_.value)
  }

  def getWidgetType(style: Styling): Option[NodeType] = {
    StylingCollector.getWidgetStyling(style)
    .map(_.value)
    .flatMap(_.widgetType)
  }
}

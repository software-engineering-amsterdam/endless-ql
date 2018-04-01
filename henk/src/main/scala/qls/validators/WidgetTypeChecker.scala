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
      val widgetType = getWidgetType(decl.styling).map(w => {
        if(w != decl.nodeType) {
          val message = "Defaultdecl type and widget type are not compatible"
          return Some(new IncompatibleWidgetType(message))
        }
      })
    })
    None
  }

  def getWidgetType(style: Styling): Option[NodeType] = {
    StylingCollector.getWidgetStyling(style)
    .map(_.value)
    .flatMap(_.widgetType)
  }
}

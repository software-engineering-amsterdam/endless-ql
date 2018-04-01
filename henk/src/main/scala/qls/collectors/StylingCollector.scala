package qls.collectors

import qls.models.ast._

import scala.collection.JavaConversions._

object StylingCollector {
  def getStyling(node: Styling): List[StylingConfiguration] = {
    flattenStyling(node)
  }

  def getWidgetStyling(node: Styling): Option[WidgetStyling] = {
    val result = getStyling(node).collect {
      case ws: WidgetStyling => ws
    }
    result.headOption
  }

  def flattenStyling(node: Styling): List[StylingConfiguration] = {
    node match {
      case s: Styling => s.configuration
    }
  }
}

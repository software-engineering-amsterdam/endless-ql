package qls.models.ast

import ql.models.ast.{ BooleanType, StringType, IntegerType, NodeType }
import qls.models._

sealed trait DisplayItem

case class Section(title: StringValue, content: List[DisplayItem])
    extends DisplayItem

case class Question(identifier: Identifier,
                    styling: Option[Styling])
    extends DisplayItem

case class DefaultDecl(nodeType: NodeType,
                       styling: Styling)
    extends DisplayItem

case class Styling(configuration: List[StylingConfiguration]) extends DisplayItem

sealed trait StylingConfiguration {
  def value: Expression
}

case class WidthStyling(value: IntegerValue) extends StylingConfiguration
case class FontStyling(value: StringValue) extends StylingConfiguration
case class FontSizeStyling(value: IntegerValue) extends StylingConfiguration
case class ColorStyling(value: StringValue) extends StylingConfiguration
case class WidgetStyling(value: WidgetExpression) extends StylingConfiguration

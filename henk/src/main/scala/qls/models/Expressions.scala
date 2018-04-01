package qls.models.ast

import ql.models.ast.{ NodeType }

trait Expression

sealed trait ExpressionValue extends Expression
case class IntegerValue(value: Int) extends ExpressionValue
case class BooleanValue(value: Boolean) extends ExpressionValue
case class StringValue(value: String) extends ExpressionValue
case class PolarValue(value: String) extends ExpressionValue

case class Identifier(id: String) extends Expression

sealed trait WidgetExpression extends Expression {
  def widgetType: Option[NodeType]
}

case class SliderWidget(widgetType: Option[NodeType], values: List[Expression])
    extends WidgetExpression
case class SpinboxWidget(widgetType: Option[NodeType]) extends WidgetExpression
case class TextWidget(widgetType: Option[NodeType]) extends WidgetExpression
case class RadioWidget(widgetType: Option[NodeType], values: List[ExpressionValue])
    extends WidgetExpression
case class CheckboxWidget(widgetType: Option[NodeType],
                          values: List[ExpressionValue])
    extends WidgetExpression
case class DropdownWidget(widgetType: Option[NodeType],
                          values: List[ExpressionValue])
    extends WidgetExpression

package qls.models.ast

import qls.models._

sealed trait Expression

case class IntegerValue(value: Int) extends Expression
case class BooleanValue(value: Boolean) extends Expression
case class StringValue(value: String) extends Expression
case class PolarValue(value: String) extends Expression

case class Identifier(id: String) extends Expression

sealed trait WidgetExpression extends Expression {
  def widgetType: Option[NodeType]
}

case class SliderWidget(widgetType: Option[NodeType], values: List[Expression])
    extends WidgetExpression
case class SpinboxWidget(widgetType: Option[NodeType]) extends WidgetExpression
case class TextWidget(widgetType: Option[NodeType]) extends WidgetExpression
case class RadioWidget(widgetType: Option[NodeType], values: List[Expression])
    extends WidgetExpression
case class CheckboxWidget(widgetType: Option[NodeType],
                          values: List[Expression])
    extends WidgetExpression
case class DropdownWidget(widgetType: Option[NodeType],
                          values: List[Expression])
    extends WidgetExpression

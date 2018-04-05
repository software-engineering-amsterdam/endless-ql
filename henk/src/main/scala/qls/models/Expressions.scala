package qls.models.ast

import ql.models.ast.{ NodeType, IntegerType, StringType, BooleanType }

trait Expression

sealed trait ExpressionValue extends Expression
case class IntegerValue(value: Int) extends ExpressionValue
case class BooleanValue(value: Boolean) extends ExpressionValue
case class StringValue(value: String) extends ExpressionValue

case class Identifier(id: String) extends Expression

sealed trait WidgetExpression extends Expression {
  def widgetType: Option[NodeType]
  def canHold(nodeType: NodeType): Boolean
}

case class SliderWidget(widgetType: Option[NodeType], values: List[Expression])
    extends WidgetExpression {
  override def toString = "SliderWidget"
  def canHold(nodeType: NodeType): Boolean = {
    nodeType match {
      case IntegerType => true
      case _ => false
    }
  }
}

case class SpinboxWidget(widgetType: Option[NodeType]) extends WidgetExpression {
  override def toString = "SpinboxWidget"
  def canHold(nodeType: NodeType): Boolean = {
    nodeType match {
      case IntegerType => true
      case other => false
    }
  }
}

case class TextWidget(widgetType: Option[NodeType]) extends WidgetExpression {
  override def toString = "TextWidget"
  def canHold(nodeType: NodeType): Boolean = {
    nodeType match {
      case IntegerType => true
      case StringType => true
      case other => false
    }
  }
}

case class RadioWidget(widgetType: Option[NodeType])
    extends WidgetExpression {
  override def toString = "RadioWidget"
  def canHold(nodeType: NodeType): Boolean = {
    nodeType match {
      case BooleanType => true
      case other => false
    }
  }
}

case class CheckboxWidget(widgetType: Option[NodeType],
                          values: List[ExpressionValue])
    extends WidgetExpression {
  override def toString = "CheckboxWidget"
  def canHold(nodeType: NodeType): Boolean = {
    nodeType match {
      case IntegerType => true
      case StringType => true
      case BooleanType => true
    }
  }
}

case class DropdownWidget(widgetType: Option[NodeType])
    extends WidgetExpression {
  override def toString = "DropdownWidget"
  def canHold(nodeType: NodeType): Boolean = {
    nodeType match {
      case BooleanType => true
      case other => false
    }
  }
}

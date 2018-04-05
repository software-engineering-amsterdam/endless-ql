package qls.visitors

import grammar._

import ql.models.ast.{ BooleanType, StringType, IntegerType, NodeType}
import qls.models.ast._

import scala.collection.JavaConversions._

class StyleVisitor extends QLSBaseVisitor[StylingConfiguration] {

  val expressionVisitor = new ExpressionVisitor()
  val optionVisitor = new OptionValuesVisitor()

  def visitStyling(ctx: QLSParser.StylingDeclContext): Styling = {
    Option(ctx.advancedStyling) match {
      case None => Styling(List(visit(ctx.widgetStyling)))
      case other => {
        val configuration = ctx.advancedStyling.stylingConfiguration
        Styling(configuration.map(visit(_)).toList)
      }
    }
  }

  override def visitWidthStyling(
      ctx: QLSParser.WidthStylingContext): StylingConfiguration = {
    WidthStyling(IntegerValue(ctx.INTEGER_LIT.getText.toInt))
  }

  override def visitFontStyling(
      ctx: QLSParser.FontStylingContext): StylingConfiguration = {
    val fontName = ctx.STRING_LIT.getText.replace("\"", "")
    FontStyling(StringValue(fontName))
  }

  override def visitFontSizeStyling(
      ctx: QLSParser.FontSizeStylingContext): StylingConfiguration = {
    FontSizeStyling(IntegerValue(ctx.INTEGER_LIT.getText.toInt))
  }

  override def visitColorStyling(
      ctx: QLSParser.ColorStylingContext): StylingConfiguration = {
    ColorStyling(StringValue(ctx.hex_color.getText))
  }

  def infereType(value: ExpressionValue): NodeType = {
    value match {
      case IntegerValue(_) => IntegerType
      case StringValue(_) => StringType
      case BooleanValue(_) => BooleanType
    }
  }

  override def visitWidgetStyling(
      ctx: QLSParser.WidgetStylingContext): StylingConfiguration = {
    val widget = ctx.WIDGET_TYPE.getText match {
      case "spinbox" => SpinboxWidget(Some(IntegerType))
      case "radio" => {
        val options = Option(ctx.optionValues).map(optionVisitor.visit).getOrElse(List(BooleanValue(false), BooleanValue(true)))
        val returnType = options.headOption.map(infereType)
        RadioWidget(returnType)
      }
      case "checkbox" => {
        val options = Option(ctx.optionValues).map(optionVisitor.visit).getOrElse(List())
        val returnType = options.headOption.map(infereType)
        CheckboxWidget(returnType, options)
      }
      case "slider" => {
        val options = Option(ctx.optionValues).map(optionVisitor.visit).getOrElse(List())
        val returnType = options.headOption.map(infereType)
        SliderWidget(returnType, options)
      }
      case "dropdown" => {
        DropdownWidget(Some(BooleanType))
      }
      case "text" => {
        TextWidget(None)
      }
    }
    WidgetStyling(widget)
  }
}

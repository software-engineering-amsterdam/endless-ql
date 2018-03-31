package qls.visitors

import grammar._
import qls.models.ast._

import scala.collection.JavaConversions._

class StyleVisitor extends QLSBaseVisitor[StylingConfiguration] {

  val expressionVisitor = new ExpressionVisitor()
  val optionVisitor = new OptionValuesVisitor()

  def visitStyling(ctx: QLSParser.StylingDeclContext): List[StylingConfiguration] = {
    Option(ctx.advancedStyling) match {
      case None => List(visit(ctx.widgetStyling))
      case other => {
        val configuration = ctx.advancedStyling.stylingConfiguration
        configuration.map(visit(_)).toList
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

  override def visitWidgetStyling(
      ctx: QLSParser.WidgetStylingContext): StylingConfiguration = {
    val widget = ctx.WIDGET_TYPE.getText match {
      case "spinbox" => SpinboxWidget(None)
      case "radio" => {
        val options = Option(ctx.optionValues).map(optionVisitor.visit).getOrElse(List())
        RadioWidget(None, options)
      }
      case "checkbox" => {
        val options = Option(ctx.optionValues).map(optionVisitor.visit).getOrElse(List())
        CheckboxWidget(None, options)
      }
      case "slider" => {
        val options = Option(ctx.optionValues).map(optionVisitor.visit).getOrElse(List())
        SliderWidget(None, options)
      }
      case "dropdown" => {
        DropdownWidget(None, List(BooleanValue(true), BooleanValue(false)))
      }
      case "text" => {
        TextWidget(None)
      }
    }
    WidgetStyling(widget)
  }
}

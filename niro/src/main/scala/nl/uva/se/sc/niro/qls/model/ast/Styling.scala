package nl.uva.se.sc.niro.qls.model.ast

import nl.uva.se.sc.niro.qls.model.ast.style._

case class Styling(
    widgetType: Option[WidgetType] = None,
    width: Option[Width] = None,
    color: Option[Color] = None,
    fontType: Option[FontType] = None,
    fontSize: Option[FontSize] = None) {

  def ++(that: Styling): Styling = {
    Styling(
      that.widgetType.orElse(widgetType),
      that.width.orElse(width),
      that.color.orElse(color),
      that.fontType.orElse(fontType),
      that.fontSize.orElse(fontSize))
  }
}

package nl.uva.se.sc.niro.qls.model.gui

import cats.Monoid
import nl.uva.se.sc.niro.qls.model.ast.Styling
import nl.uva.se.sc.niro.qls.model.gui.style._

case class GUIStyling(
    widgetStyle: Option[GUIWidgetStyle] = None,
    width: Option[GUIWidth] = None,
    color: Option[GUIColor] = None,
    fontType: Option[GUIFontStyle] = None,
    fontSize: Option[GUIFontSize] = None) {

  def ++(that: GUIStyling) =
    GUIStyling(
      that.widgetStyle.orElse(widgetStyle),
      that.width.orElse(width),
      that.color.orElse(color),
      that.fontType.orElse(fontType),
      that.fontSize.orElse(fontSize)
    )
}

object GUIStyling {

  implicit val guiStylingMonoid: Monoid[GUIStyling] = new Monoid[GUIStyling] {
    override def empty: GUIStyling = GUIStyling()

    override def combine(x: GUIStyling, y: GUIStyling): GUIStyling =
      x ++ y
  }

  def apply(styling: Styling): GUIStyling = new GUIStyling(
    styling.widgetType.map(GUIStyle(_)),
    styling.width.map(GUIStyle(_)),
    styling.color.map(GUIStyle(_)),
    styling.fontType.map(GUIStyle(_)),
    styling.fontSize.map(GUIStyle(_))
  )
}

package nl.uva.se.sc.niro.qls.model.gui

import cats.Monoid
import nl.uva.se.sc.niro.qls.model.ast.Styling
import nl.uva.se.sc.niro.qls.model.gui.style._

case class Styling(
    widgetStyle: Option[GUIWidgetStyle] = None,
    width: Option[GUIWidth] = None,
    color: Option[GUIColor] = None,
    fontType: Option[GUIFontStyle] = None,
    fontSize: Option[GUIFontSize] = None) {

  def ++(that: Styling) =
    Styling(
      that.widgetStyle.orElse(widgetStyle),
      that.width.orElse(width),
      that.color.orElse(color),
      that.fontType.orElse(fontType),
      that.fontSize.orElse(fontSize)
    )
}

object Styling {

  implicit val guiStylingMonoid: Monoid[Styling] = new Monoid[Styling] {
    override def empty: Styling = Styling()

    override def combine(x: Styling, y: Styling): Styling =
      x ++ y
  }

  def apply(styling: nl.uva.se.sc.niro.qls.model.ast.Styling): Styling = new Styling(
    styling.widgetType.map(GUIStyle(_)),
    styling.width.map(GUIStyle(_)),
    styling.color.map(GUIStyle(_)),
    styling.fontType.map(GUIStyle(_)),
    styling.fontSize.map(GUIStyle(_))
  )
}

package nl.uva.se.sc.niro.qls.model.gui

import cats.Monoid
import nl.uva.se.sc.niro.qls.model.gui.style._

case class Styling(
                    widgetStyle: Option[WidgetType] = None,
                    width: Option[Width] = None,
                    color: Option[Color] = None,
                    fontType: Option[FontType] = None,
                    fontSize: Option[FontSize] = None) {

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
    styling.widgetType.map(Style(_)),
    styling.width.map(Style(_)),
    styling.color.map(Style(_)),
    styling.fontType.map(Style(_)),
    styling.fontSize.map(Style(_))
  )
}

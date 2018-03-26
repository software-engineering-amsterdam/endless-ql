package nl.uva.se.sc.niro.model.gui

/**
  * Model used by the frontend
  */
import cats.Monoid
import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.qls._

case class GUIStylesheet(name: String, pages: Seq[GUIPage], defaultStyles: Map[AnswerType, GUIStyling])

case class GUIPage(name: String, sections: Seq[GUISection], defaultStyles: Map[AnswerType, GUIStyling])

case class GUISection(name: String, questions: Seq[GUIStyledQuestion], defaultStyles: Map[AnswerType, GUIStyling])

case class GUIStyledQuestion(name: String, style: GUIStyling)

abstract class GUIStyle

abstract class GUIWidgetStyle extends GUIStyle
case class GUIDefaultWidget() extends GUIWidgetStyle
case class GUISpinBoxStyle() extends GUIWidgetStyle
case class GUIComboBoxStyle(trueLabel: String, falseLabel: String) extends GUIWidgetStyle
case class GUIRadioStyle(trueLabel: String, falseLabel: String) extends GUIWidgetStyle

case class GUIFontStyle(fontType: String) extends GUIStyle
case class GUIFontSize(fontSize: Int) extends GUIStyle
case class GUIColor(color: String) extends GUIStyle
case class GUIWidth(width: Int) extends GUIStyle

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
}

object GUIStyle {

  def apply(widgetType: Option[WidgetType]): GUIWidgetStyle = widgetType.map(GUIStyle(_)).getOrElse(GUIDefaultWidget())

  def apply(widgetType: WidgetType): GUIWidgetStyle = widgetType match {
    case SpinBox()                       => GUISpinBoxStyle()
    case ComboBox(trueValue, falseValue) => GUIComboBoxStyle(trueValue, falseValue)
    case Radio(trueValue, falseValue)    => GUIRadioStyle(trueValue, falseValue)
    case CheckBox()                      => GUIDefaultWidget()
  }

  def apply(font: FontType): GUIFontStyle = GUIFontStyle(font.name)

  def apply(fontSize: FontSize) = GUIFontSize(fontSize.size)

  def apply(color: Color) = GUIColor(color.color)

  def apply(width: Width) = GUIWidth(width.width)

  def apply(styling: Styling): GUIStyling = GUIStyling(
    styling.widgetType.map(GUIStyle(_)),
    styling.width.map(GUIStyle(_)),
    styling.color.map(GUIStyle(_)),
    styling.fontType.map(GUIStyle(_)),
    styling.fontSize.map(GUIStyle(_))
  )
}

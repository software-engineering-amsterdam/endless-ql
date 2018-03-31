package nl.uva.se.sc.niro.qls.model.gui.style

import nl.uva.se.sc.niro.qls.model.ast.style._

abstract class GUIStyle

object GUIStyle {

  def apply(widgetType: Option[WidgetType]): GUIWidgetStyle = widgetType.map(GUIStyle(_)).getOrElse(GUIDefaultWidget())

  def apply(widgetType: WidgetType): GUIWidgetStyle = widgetType match {
    case SpinBox(minimum, maximum, stepSize) => GUISpinBoxStyle(minimum, maximum, stepSize)
    case Slider(minimum, maximum)            => GUISliderStyle(minimum, maximum)
    case ComboBox(trueValue, falseValue)     => GUIComboBoxStyle(trueValue, falseValue)
    case Radio(trueValue, falseValue)        => GUIRadioStyle(trueValue, falseValue)
    case CheckBox()                          => GUIDefaultWidget()
  }

  def apply(font: FontType): GUIFontStyle = GUIFontStyle(font.name)

  def apply(fontSize: FontSize) = GUIFontSize(fontSize.size)

  def apply(color: Color) = GUIColor(color.color)

  def apply(width: Width) = GUIWidth(width.width)

}

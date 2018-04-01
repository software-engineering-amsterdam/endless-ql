package nl.uva.se.sc.niro.qls.model.gui.style

abstract class Style

object Style {

  def apply(widgetType: Option[nl.uva.se.sc.niro.qls.model.ast.style.WidgetType]): WidgetType = widgetType.map(Style(_)).getOrElse(DefaultWidget())

  def apply(widgetType: nl.uva.se.sc.niro.qls.model.ast.style.WidgetType): WidgetType = widgetType match {
    case nl.uva.se.sc.niro.qls.model.ast.style.SpinBox(minimum, maximum, stepSize) => SpinBox(minimum, maximum, stepSize)
    case nl.uva.se.sc.niro.qls.model.ast.style.Slider(minimum, maximum)            => Slider(minimum, maximum)
    case nl.uva.se.sc.niro.qls.model.ast.style.ComboBox(trueValue, falseValue)     => ComboBox(trueValue, falseValue)
    case nl.uva.se.sc.niro.qls.model.ast.style.Radio(trueValue, falseValue)        => Radio(trueValue, falseValue)
    case nl.uva.se.sc.niro.qls.model.ast.style.CheckBox()                          => DefaultWidget()
  }

  def apply(font: nl.uva.se.sc.niro.qls.model.ast.style.FontType): FontType = FontType(font.name)

  def apply(fontSize: nl.uva.se.sc.niro.qls.model.ast.style.FontSize) = FontSize(fontSize.size)

  def apply(color: nl.uva.se.sc.niro.qls.model.ast.style.Color) = Color(color.color)

  def apply(width: nl.uva.se.sc.niro.qls.model.ast.style.Width) = Width(width.width)

}

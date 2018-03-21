package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.qls._

class Style

case class DefaultStyle() extends Style
case class SpinBoxStyle() extends Style
case class ComboBoxStyle(trueLabel: String, falseLabel: String) extends Style
case class RadioStyle(trueLabel: String, falseLabel: String) extends Style

object Style {

  def apply(widgetType: Option[WidgetType]): Style = widgetType.map(Style(_)).getOrElse(DefaultStyle())

  def apply(widgetType: WidgetType): Style = widgetType match {
    case SpinBox()                       => SpinBoxStyle()
    case ComboBox(trueValue, falseValue) => ComboBoxStyle(trueValue, falseValue)
    case Radio(trueValue, falseValue)    => RadioStyle(trueValue, falseValue)
  }
}

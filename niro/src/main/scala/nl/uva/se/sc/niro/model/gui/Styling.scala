package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.qls._

class Styling

case class DefaultStyle() extends Styling
case class SpinBoxStyle() extends Styling
case class ComboBoxStyle(trueLabel: String, falseLabel: String) extends Styling
case class RadioStyle(trueLabel: String, falseLabel: String) extends Styling

object Styling {

  def apply(widgetType: Option[WidgetType]): Styling = widgetType.map(Styling(_)).getOrElse(DefaultStyle())

  def apply(widgetType: WidgetType): Styling = widgetType match {
    case SpinBox()                       => SpinBoxStyle()
    case ComboBox(trueValue, falseValue) => ComboBoxStyle(trueValue, falseValue)
    case Radio(trueValue, falseValue)    => RadioStyle(trueValue, falseValue)
  }
}

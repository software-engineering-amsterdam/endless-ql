package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.qls._

class Styling

case class Default() extends Styling
case class SpinBox() extends Styling
case class ComboBox(trueLabel: String, falseLabel: String) extends Styling
case class Radio(trueLabel: String, falseLabel: String) extends Styling

object Styling {
  def apply(widgetType: QLSWidgetType): Styling = {
    widgetType match {
      case QLSSpinBox()                       => SpinBox()
      case QLSComboBox(trueValue, falseValue) => ComboBox(trueValue, falseValue)
      case QLSRadio(trueValue, falseValue)    => Radio(trueValue, falseValue)
      case _                                  => Default()
    }
  }
}

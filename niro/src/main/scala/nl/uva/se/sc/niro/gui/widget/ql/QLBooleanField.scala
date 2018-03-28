package nl.uva.se.sc.niro.gui.widget.ql

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.CheckBox

class QLBooleanField extends CheckBox with QLWidget[Boolean] {
  selectedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      valueChanged()
  })
  override def value(value: Boolean): Unit = setSelected(value)
  override def value: Boolean = isSelected
}

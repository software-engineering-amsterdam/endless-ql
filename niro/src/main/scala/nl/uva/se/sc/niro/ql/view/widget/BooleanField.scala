package nl.uva.se.sc.niro.ql.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.CheckBox

class BooleanField extends CheckBox with Widget[java.lang.Boolean] {
  selectedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      valueChanged()
  })
  override def value(value: java.lang.Boolean): Unit = setSelected(value)
  override def value: java.lang.Boolean = isSelected
}

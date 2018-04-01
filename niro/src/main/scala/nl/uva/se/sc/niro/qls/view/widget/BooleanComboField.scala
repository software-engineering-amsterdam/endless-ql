package nl.uva.se.sc.niro.qls.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.collections.FXCollections
import javafx.scene.control.ChoiceBox
import javafx.util.StringConverter
import nl.uva.se.sc.niro.ql.view.widget.Widget

class BooleanComboField(trueLabel: String, falseLabel: String)
    extends ChoiceBox[java.lang.Boolean]
    with Widget[java.lang.Boolean] {
  setItems(FXCollections.observableArrayList(java.lang.Boolean.TRUE, java.lang.Boolean.FALSE))
  setConverter(new StringConverter[java.lang.Boolean]() {
    override def toString(value: java.lang.Boolean): String = if (value) trueLabel else falseLabel
    override def fromString(value: String): java.lang.Boolean = value == trueLabel
  })
  valueProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      valueChanged()
  })
  override def value(newValue: java.lang.Boolean): Unit = setValue(newValue)
  override def value: java.lang.Boolean = getValue
}

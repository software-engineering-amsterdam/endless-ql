package nl.uva.se.sc.niro.gui.control.qls

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.collections.FXCollections
import javafx.scene.control.ChoiceBox
import javafx.util.StringConverter
import nl.uva.se.sc.niro.gui.control.ql.QLWidget

class QLSBooleanComboField(trueLabel: String, falseLabel: String) extends ChoiceBox[Boolean] with QLWidget[Boolean] {
  setItems(FXCollections.observableArrayList(true, false))
  setConverter(new StringConverter[Boolean]() {
    override def toString(value: Boolean): String = if (value) trueLabel else falseLabel
    override def fromString(value: String): Boolean = value == trueLabel
  })
  valueProperty().addListener(new ChangeListener[Boolean] {
    override def changed(observable: ObservableValue[_ <: Boolean], oldValue: Boolean, newValue: Boolean): Unit =
      valueChanged()
  })
  override def value(newValue: Boolean): Unit = setValue(newValue)
  override def value: Boolean = getValue
}

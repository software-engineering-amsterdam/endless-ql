package nl.uva.se.sc.niro.gui.control.qls

import java.lang

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory
import nl.uva.se.sc.niro.gui.control.ql.QLWidget

class QLSIntegerSpinField()
  extends Spinner[Integer](new IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0))
    with QLWidget[java.math.BigInteger] {
  setEditable(true)
  valueProperty().addListener(new ChangeListener[Integer] {
    override def changed(observable: ObservableValue[_ <: Integer], oldValue: Integer, newValue: Integer): Unit =
      valueChanged()
  })
  focusedProperty().addListener(new ChangeListener[lang.Boolean] {
    override def changed(
                          observable: ObservableValue[_ <: lang.Boolean],
                          oldValue: lang.Boolean,
                          newValue: lang.Boolean): Unit = {
      if (!newValue) {
        increment(0)
        valueChanged()
      }
    }
  })
  override def value(newValue: java.math.BigInteger): Unit =
    if (newValue != null) getValueFactory.valueProperty().setValue(newValue.intValue())
  override def value: java.math.BigInteger =
    java.math.BigInteger.valueOf(getValueFactory.valueProperty().getValue.longValue())
}

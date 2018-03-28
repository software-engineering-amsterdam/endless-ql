package nl.uva.se.sc.niro.gui.widget.qls

import java.lang

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Spinner
import nl.uva.se.sc.niro.gui.widget.{ FormatMasks, IntegerFormatterBuilder }
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget

class QLSIntegerSpinField()
    extends Spinner[Integer](Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1)
    with QLWidget[java.math.BigInteger]
    with FormatMasks {

  setEditable(true)
  getEditor.setTextFormatter(IntegerFormatterBuilder().buildInputFilter(INTEGER_MASK).buildConverter().build())
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

package nl.uva.se.sc.niro.gui.widget.qls

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Spinner
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget
import nl.uva.se.sc.niro.gui.widget.{ FormatMasks, IntegerFormatterBuilder }

class QLSIntegerSpinField()
    extends Spinner[Integer](Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1)
    with QLWidget[java.math.BigInteger]
    with FormatMasks {

  getEditor.setTextFormatter(IntegerFormatterBuilder().buildInputFilter(INTEGER_MASK).buildConverter().build())
  valueProperty().addListener(new ChangeListener[Integer] {
    override def changed(observable: ObservableValue[_ <: Integer], oldValue: Integer, newValue: Integer): Unit =
      valueChanged()
  })

  override def value(newValue: java.math.BigInteger): Unit =
    if (newValue != null) getValueFactory.valueProperty().setValue(newValue.intValue())

  override def value: java.math.BigInteger =
    java.math.BigInteger.valueOf(getValueFactory.valueProperty().getValue.longValue())
}

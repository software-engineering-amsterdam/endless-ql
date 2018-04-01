package nl.uva.se.sc.niro.qls.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Spinner
import nl.uva.se.sc.niro.ql.view.format.{ FormatMasks, IntegerFormatterBuilder }
import nl.uva.se.sc.niro.ql.view.widget.Widget

class IntegerSpinField(minimum: Integer, maximum: Integer, stepSize: Integer)
    extends Spinner[Integer](minimum, maximum, minimum, stepSize)
    with Widget[java.math.BigInteger]
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

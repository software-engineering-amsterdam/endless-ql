package nl.uva.se.sc.niro.gui.widget.qls

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Spinner
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget
import nl.uva.se.sc.niro.gui.widget.{ DecimalFormatterBuilder, FormatMasks }

class QLSMoneySpinField(minimum: Double, maximum: Double, stepSize: Double)
    extends Spinner[Double](minimum, maximum, (maximum - minimum) / 2, stepSize)
    with QLWidget[java.math.BigDecimal]
    with FormatMasks {

  getEditor.setTextFormatter(DecimalFormatterBuilder().buildInputFilter(MONEY_MASK).buildConverter().build())
  valueProperty().addListener(new ChangeListener[Double] {
    override def changed(observable: ObservableValue[_ <: Double], oldValue: Double, newValue: Double): Unit =
      valueChanged()
  })

  override def value(newValue: java.math.BigDecimal): Unit =
    if (newValue != null) getValueFactory.valueProperty().setValue(newValue.doubleValue())

  override def value: java.math.BigDecimal =
    java.math.BigDecimal.valueOf(getValueFactory.valueProperty().getValue.doubleValue())
}

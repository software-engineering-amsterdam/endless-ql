package nl.uva.se.sc.niro.qls.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Slider
import nl.uva.se.sc.niro.ql.view.format.FormatMasks
import nl.uva.se.sc.niro.ql.view.widget.Widget

class DecimalSliderField(minimum: Double, maximum: Double)
    extends Slider()
    with Widget[java.math.BigDecimal]
    with FormatMasks {

  setMin(minimum)
  setMax(maximum)

  setShowTickLabels(true)
  setShowTickMarks(true)

  valueProperty().addListener(new ChangeListener[Number] {
    override def changed(observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number): Unit =
      valueChanged()
  })

  override def value(newValue: java.math.BigDecimal): Unit =
    if (newValue != null) valueProperty().setValue(newValue)

  override def value: java.math.BigDecimal =
    java.math.BigDecimal.valueOf(valueProperty().getValue)
}

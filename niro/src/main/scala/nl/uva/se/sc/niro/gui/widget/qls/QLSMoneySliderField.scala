package nl.uva.se.sc.niro.gui.widget.qls

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Slider
import nl.uva.se.sc.niro.gui.widget.FormatMasks
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget

class QLSMoneySliderField()
    extends Slider()
    with QLWidget[java.math.BigDecimal]
    with FormatMasks {

  setMin(-10)
  setMax(10)
  setBlockIncrement(1)
  setMajorTickUnit(1)
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

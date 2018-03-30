package nl.uva.se.sc.niro.gui.widget.ql

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.TextField

abstract class AbstractQLTextField[T]() extends TextField with QLWidget[T] {
  focusedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      if (oldValue) valueChanged()
  })
}

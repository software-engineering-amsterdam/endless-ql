package nl.uva.se.sc.niro.ql.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }

abstract class AbstractTextField[T]() extends javafx.scene.control.TextField with Widget[T] {
  focusedProperty().addListener(new ChangeListener[java.lang.Boolean] {
    override def changed(
        observable: ObservableValue[_ <: java.lang.Boolean],
        oldValue: java.lang.Boolean,
        newValue: java.lang.Boolean): Unit =
      if (oldValue) valueChanged()
  })
}

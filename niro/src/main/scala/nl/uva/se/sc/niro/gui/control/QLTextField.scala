package nl.uva.se.sc.niro.gui.control

import javafx.beans.value.{ ChangeListener, ObservableValue }

class QLTextField() extends AbstractQLTextField[String] {
  textProperty().addListener(new ChangeListener[String] {
    override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit =
      valueChanged()
  })
  override def value(value: String): Unit = setText(value)
  override def value: String = getText
}

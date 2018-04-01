package nl.uva.se.sc.niro.ql.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }

class TextField() extends AbstractTextField[String] {
  textProperty().addListener(new ChangeListener[String] {
    override def changed(observable: ObservableValue[_ <: String], oldValue: String, newValue: String): Unit =
      valueChanged()
  })
  override def value(value: String): Unit = setText(value)
  override def value: String = getText
}

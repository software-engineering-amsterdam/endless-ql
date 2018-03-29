package nl.uva.se.sc.niro.gui.widget.qls

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.geometry.Insets
import javafx.scene.control.{ RadioButton, Toggle, ToggleGroup }
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget

class QLSBooleanRadioField(trueLabel: String, falseLabel: String) extends HBox with QLWidget[java.lang.Boolean] {
  val group = new ToggleGroup()
  val trueChoice = new RadioButton(trueLabel)
  val falseChoice = new RadioButton(falseLabel)

  trueChoice.setToggleGroup(group)
  falseChoice.setToggleGroup(group)
  getChildren.addAll(trueChoice, falseChoice)

  setPadding(new Insets(3.0, 0.0, 5.0, 0.0))
  setSpacing(5.0)

  override def value(value: java.lang.Boolean): Unit = group.selectToggle(if (value) trueChoice else falseChoice)
  override def value: java.lang.Boolean = group.getSelectedToggle == trueChoice
  group
    .selectedToggleProperty()
    .addListener(new ChangeListener[Toggle] {
      override def changed(observable: ObservableValue[_ <: Toggle], oldValue: Toggle, newValue: Toggle): Unit =
        valueChanged()
    })
}

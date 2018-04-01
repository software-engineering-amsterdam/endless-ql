package nl.uva.se.sc.niro.qls.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.geometry.Insets
import javafx.scene.control.{ RadioButton, Toggle, ToggleGroup }
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.ql.view.widget.Widget

class BooleanRadioField(trueLabel: String, falseLabel: String) extends HBox with Widget[java.lang.Boolean] {
  val group = new ToggleGroup()
  val trueChoice = new RadioButton(trueLabel)
  val falseChoice = new RadioButton(falseLabel)

  trueChoice.setToggleGroup(group)
  falseChoice.setToggleGroup(group)
  getChildren.addAll(trueChoice, falseChoice)
  group
    .selectedToggleProperty()
    .addListener(new ChangeListener[Toggle] {
      override def changed(observable: ObservableValue[_ <: Toggle], oldValue: Toggle, newValue: Toggle): Unit =
      valueChanged()
    })

  setPadding(new Insets(3.0, 0.0, 5.0, 0.0))
  setSpacing(5.0)


  override def value(value: java.lang.Boolean): Unit = group.selectToggle(if (value) trueChoice else falseChoice)

  override def value: java.lang.Boolean = group.getSelectedToggle == trueChoice

}

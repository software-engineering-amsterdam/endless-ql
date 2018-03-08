package nl.uva.se.sc.niro.gui.widgets

import javafx.scene.control.{ Control, Label }
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.gui.factory.WidgetFactory
import nl.uva.se.sc.niro.model.gui.GUIQuestion

class Component(label: Label, control: Control) extends HBox {
  getChildren.addAll(label, control)
  managedProperty().bind(visibleProperty())

  label.setPrefWidth(200)
  control.setPrefWidth(200)

  def setReadOnly(value: Boolean): Unit = control.setDisable(value)
}

object ComponentFactory {
  def make(question: GUIQuestion): Component = {
    val component = new Component(new Label(question.label), WidgetFactory.make(question))
    component.setReadOnly(question.isReadOnly)
    component
  }
}

package nl.uva.se.sc.niro.gui.widgets

import javafx.scene.control.{ Control, Label }
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.gui.WidgetFactory
import nl.uva.se.sc.niro.model.gui.GUIQuestion

class Component(label: Label, control: Control) extends HBox {
  getChildren.addAll(label, control)
  label.setPrefWidth(200)
  control.setPrefWidth(200)
}

object ComponentFactory {
  def make(question: GUIQuestion): Component = {
    new Component(new Label(question.label), WidgetFactory.make(question))
  }
}

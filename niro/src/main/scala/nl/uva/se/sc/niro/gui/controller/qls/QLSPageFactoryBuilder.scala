package nl.uva.se.sc.niro.gui.controller.qls

import javafx.scene.Node
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.component.qls.{ QLSPageFactory, QLSPageFactoryViewUpdateDecorator }
import nl.uva.se.sc.niro.model.gui.ql.GUIForm
import nl.uva.se.sc.niro.model.gui.qls.GUIStylesheet

class QLSPageFactoryBuilder(controller: QLSFormController, guiForm: GUIForm, stylesheet: GUIStylesheet) {
  def build: Callback[Integer, Node] = {
    new QLSPageFactoryViewUpdateDecorator(controller, new QLSPageFactory(controller, guiForm, stylesheet))
  }
}

package nl.uva.se.sc.niro.gui.component.qls

import javafx.scene.Node
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.controller.ql.ViewUpdater

class QLSPageFactoryViewUpdateDecorator(viewUpdater: ViewUpdater, pageFactory: Callback[Integer, Node]) extends Callback[Integer, Node]() {
  override def call(pageNumber: Integer): Node = {
    try {
      pageFactory.call(pageNumber)
    } finally {
      viewUpdater.updateView
    }
  }
}

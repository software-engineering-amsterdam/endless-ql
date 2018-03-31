package nl.uva.se.sc.niro.qls.view.component

import javafx.scene.Node
import javafx.util.Callback
import nl.uva.se.sc.niro.ql.controller.ViewUpdater

class QLSPageFactoryViewUpdateDecorator(viewUpdater: ViewUpdater, pageFactory: Callback[Integer, Node])
    extends Callback[Integer, Node]() {
  override def call(pageNumber: Integer): Node = {
    try {
      pageFactory.call(pageNumber)
    } finally {
      viewUpdater.updateView()
    }
  }
}

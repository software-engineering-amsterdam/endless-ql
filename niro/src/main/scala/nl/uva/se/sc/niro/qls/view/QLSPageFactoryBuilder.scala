package nl.uva.se.sc.niro.qls.view

import javafx.scene.Node
import javafx.util.Callback
import nl.uva.se.sc.niro.ql.model.gui.GUIForm
import nl.uva.se.sc.niro.ql.view.component.ComponentFactory
import nl.uva.se.sc.niro.qls.controller.QLSFormController
import nl.uva.se.sc.niro.qls.model.gui.GUIStylesheet
import nl.uva.se.sc.niro.qls.view.component.{ QLSPageFactory, QLSPageFactoryViewUpdateDecorator }

class QLSPageFactoryBuilder() {
  private var controller: QLSFormController = _
  private var guiForm: GUIForm = _
  private var stylesheet: GUIStylesheet = _
  private var componentFactory: ComponentFactory = _

  def buildWith(controller: QLSFormController): QLSPageFactoryBuilder = {
    this.controller = controller
    this
  }

  def buildWith(guiForm: GUIForm): QLSPageFactoryBuilder = {
    this.guiForm = guiForm
    this
  }

  def buildWith(stylesheet: GUIStylesheet): QLSPageFactoryBuilder = {
    this.stylesheet = stylesheet
    this
  }

  def buildWith(componentFactory: ComponentFactory): QLSPageFactoryBuilder = {
    this.componentFactory = componentFactory
    this
  }

  def build: Callback[Integer, Node] = {
    new QLSPageFactoryViewUpdateDecorator(
      controller,
      new QLSPageFactory(controller, guiForm, stylesheet, componentFactory))
  }
}

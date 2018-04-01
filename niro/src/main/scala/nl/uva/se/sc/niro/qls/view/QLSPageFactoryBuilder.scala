package nl.uva.se.sc.niro.qls.view

import javafx.scene.Node
import javafx.util.Callback
import nl.uva.se.sc.niro.ql.model.gui.Form
import nl.uva.se.sc.niro.ql.view.component.ComponentFactory
import nl.uva.se.sc.niro.qls.controller.QLSFormController
import nl.uva.se.sc.niro.qls.model.gui.Stylesheet
import nl.uva.se.sc.niro.qls.view.component.{ QLSPageFactory, QLSPageFactoryViewUpdateDecorator }

class QLSPageFactoryBuilder() {
  private var controller: QLSFormController = _
  private var guiForm: Form = _
  private var stylesheet: Stylesheet = _
  private var componentFactory: ComponentFactory = _

  def buildWith(controller: QLSFormController): QLSPageFactoryBuilder = {
    this.controller = controller
    this
  }

  def buildWith(guiForm: Form): QLSPageFactoryBuilder = {
    this.guiForm = guiForm
    this
  }

  def buildWith(stylesheet: Stylesheet): QLSPageFactoryBuilder = {
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

package nl.uva.se.sc.niro.ql.view.component

import nl.uva.se.sc.niro.ql.model.gui.Question

class ComponentFactoryIsReadOnlyDecorator(componentFactory: ComponentFactory) extends ComponentFactory {
  override def make(question: Question): Component[_] = {
    val component = componentFactory.make(question)
    component.setReadOnly(question.isReadOnly)
    component
  }
}

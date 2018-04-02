package nl.uva.se.sc.niro.ql.view.component

import nl.uva.se.sc.niro.ql.model.gui.Question
import nl.uva.se.sc.niro.ql.view.ComponentChangedListener

class ComponentFactoryAddValueChangedListenerDecorator(
    componentChangeListener: ComponentChangedListener,
    componentFactory: ComponentFactory)
    extends ComponentFactory {

  override def make(question: Question): Component[_] = {
    val component = componentFactory.make(question)
    component.addComponentChangedListener(componentChangeListener)
    component
  }
}

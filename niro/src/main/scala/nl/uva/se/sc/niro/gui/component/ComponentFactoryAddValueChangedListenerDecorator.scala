package nl.uva.se.sc.niro.gui.component
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.ql.model.gui.GUIQuestion

class ComponentFactoryAddValueChangedListenerDecorator(
    componentChangeListener: ComponentChangedListener,
    componentFactory: ComponentFactory)
    extends ComponentFactory {
  override def make(question: GUIQuestion): Component[_] = {
    val component = componentFactory.make(question)
    component.addComponentChangedListener(componentChangeListener)
    component
  }
}

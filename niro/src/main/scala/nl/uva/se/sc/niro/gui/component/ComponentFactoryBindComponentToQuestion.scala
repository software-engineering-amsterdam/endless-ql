package nl.uva.se.sc.niro.gui.component
import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion

class ComponentFactoryBindComponentToQuestion(componentFactory: ComponentFactory) extends ComponentFactory {
  override def make(question: GUIQuestion): Component[_] = {
    val component = componentFactory.make(question)
    question.component = Some(component)
    component
  }
}

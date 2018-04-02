package nl.uva.se.sc.niro.ql.view.component

import nl.uva.se.sc.niro.ql.model.gui.Question

class ComponentFactoryBindComponentToQuestion(componentFactory: ComponentFactory) extends ComponentFactory {

  override def make(question: Question): Component[_] = {
    val component = componentFactory.make(question)
    question.component = Some(component)
    component
  }
}

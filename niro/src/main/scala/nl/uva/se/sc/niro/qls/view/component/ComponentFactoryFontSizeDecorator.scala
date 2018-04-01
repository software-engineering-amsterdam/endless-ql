package nl.uva.se.sc.niro.qls.view.component

import nl.uva.se.sc.niro.ql.model.gui.Question
import nl.uva.se.sc.niro.ql.view.component.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.qls.model.gui.QLSQuestion

class ComponentFactoryFontSizeDecorator(componentFactory: ComponentFactory) extends ComponentFactory {
  override def make(question: Question): Component[_] = question match {
    case qlsQuestion: QLSQuestion =>
      val component = componentFactory.make(question)
      qlsQuestion.styling.fontSize.foreach(fontSize =>
        component.setStyle(s"-fx-font-size: ${fontSize.fontSize}; ${component.getStyle}"))
      component
    case _ => componentFactory.make(question)
  }
}

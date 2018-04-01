package nl.uva.se.sc.niro.qls.view.component

import nl.uva.se.sc.niro.ql.model.gui.Question
import nl.uva.se.sc.niro.ql.view.component.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.qls.model.gui.QLSQuestion

import scala.collection.JavaConverters

class ComponentFactoryColorDecorator(componentFactory: ComponentFactory) extends ComponentFactory {
  override def make(question: Question): Component[_] = question match {
    case qlsQuestion: QLSQuestion =>
      val component = componentFactory.make(question)
      val nodes = JavaConverters.asScalaBuffer(component.getChildren)
      qlsQuestion.styling.color.foreach(color => {
        nodes.foreach(child => child.setStyle(s"-fx-text-fill: ${color.color}; ${child.getStyle}"))
      })
      component
    case _ => componentFactory.make(question)
  }
}

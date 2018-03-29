package nl.uva.se.sc.niro.gui.component.qls

import nl.uva.se.sc.niro.gui.component.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion
import nl.uva.se.sc.niro.model.gui.qls.QLSGUIQuestion

import scala.collection.JavaConverters

class ComponentFactoryColorDecorator(componentFactory: ComponentFactory) extends ComponentFactory {
  override def make(question: GUIQuestion): Component[_] = question match {
    case qlsQuestion: QLSGUIQuestion =>
      val component = componentFactory.make(question)
      val nodes = JavaConverters.asScalaBuffer(component.getChildren)
      qlsQuestion.styling.color.foreach(color => {
        nodes.foreach(child => child.setStyle(s"-fx-text-fill: ${color.color}; ${child.getStyle}"))
      })
      component
    case _ => componentFactory.make(question)
  }
}

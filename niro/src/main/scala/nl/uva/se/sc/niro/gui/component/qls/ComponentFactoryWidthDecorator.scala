package nl.uva.se.sc.niro.gui.component.qls

import nl.uva.se.sc.niro.gui.component.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.ql.model.gui.GUIQuestion
import nl.uva.se.sc.niro.qls.model.gui.QLSGUIQuestion

import scala.collection.JavaConverters

class ComponentFactoryWidthDecorator(componentFactory: ComponentFactory) extends ComponentFactory {
  override def make(question: GUIQuestion): Component[_] = question match {
    case qlsQuestion: QLSGUIQuestion =>
      val component = componentFactory.make(question)
      val nodes = JavaConverters.asScalaBuffer(component.getChildren)
      qlsQuestion.styling.width.foreach(width => {
        nodes.foreach(child => child.setStyle(s"-fx-pref-width: ${width.width}px; ${child.getStyle}"))
      })
      component
    case _ => componentFactory.make(question)
  }
}

package nl.uva.se.sc.niro.gui.component.qls

import nl.uva.se.sc.niro.gui.component.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion
import nl.uva.se.sc.niro.model.gui.qls.QLSGUIQuestion

class ComponentFactoryFontTypeDecorator(componentFactory: ComponentFactory) extends ComponentFactory {
  override def make(question: GUIQuestion): Component[_] = question match {
    case qlsQuestion: QLSGUIQuestion => {
      val component = componentFactory.make(question)
      qlsQuestion.styling.fontType.foreach(font =>
        component.setStyle(s"-fx-font-family: ${font.fontType}; ${component.getStyle}"))
      component
    }
    case _ => componentFactory.make(question)
  }
}

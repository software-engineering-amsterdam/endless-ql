package nl.uva.se.sc.niro.gui.component.qls

import nl.uva.se.sc.niro.gui.component.Component
import nl.uva.se.sc.niro.gui.component.ql.QLComponentFactory
import nl.uva.se.sc.niro.gui.widget.qls.QLSWidgetFactory
import nl.uva.se.sc.niro.gui.widget.WidgetFactory
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion
import nl.uva.se.sc.niro.model.gui.qls.QLSGUIQuestion

import scala.collection.JavaConverters

class QLSComponentFactory(componentChangeListener: ComponentChangedListener, widgetFactory: WidgetFactory)
    extends QLComponentFactory(componentChangeListener, widgetFactory) {
  override def make(question: GUIQuestion): Component[_] = {
    question match {
      case qlsQuestion: QLSGUIQuestion => {
        // TODO convert to decorators
        val component = super.make(question)
        val nodes = JavaConverters.asScalaBuffer(component.getChildren)
        qlsQuestion.styling.color.foreach(color => {
          nodes.foreach(child => child.setStyle(s"-fx-text-fill: ${color.color}; ${child.getStyle}"))
        })
        qlsQuestion.styling.width.foreach(width => {
          nodes.foreach(child => child.setStyle(s"-fx-pref-width: ${width.width}px; ${child.getStyle}"))
        })
        qlsQuestion.styling.fontType.foreach(font =>
          component.setStyle(s"-fx-font-family: ${font.fontType}; ${component.getStyle}"))
        qlsQuestion.styling.fontSize.foreach(fontSize =>
          component.setStyle(s"-fx-font-size: ${fontSize.fontSize}; ${component.getStyle}"))
        component
      }
      case _ => super.make(question)
    }
  }
}

object QLSComponentFactory {
  def apply(componentChangeListener: ComponentChangedListener) =
    new QLSComponentFactory(componentChangeListener, new QLSWidgetFactory())
}

package nl.uva.se.sc.niro.gui.component.qls

import nl.uva.se.sc.niro.gui.component.ql.QLComponentFactory
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.gui.widget.WidgetFactory

object QLSComponentFactory {
  def apply(componentChangeListener: ComponentChangedListener, widgetFactory: WidgetFactory) =
    new ComponentFactoryFontSizeDecorator(
      new ComponentFactoryFontTypeDecorator(
        new ComponentFactoryWidthDecorator(
          new ComponentFactoryColorDecorator(QLComponentFactory(componentChangeListener, widgetFactory)))))
}

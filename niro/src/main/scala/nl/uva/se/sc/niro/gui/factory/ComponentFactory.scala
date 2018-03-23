package nl.uva.se.sc.niro.gui.factory

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.model.gui.{ GUIQuestion, QLSGUIQuestion }
import nl.uva.se.sc.niro.model.ql._

import scala.collection.JavaConverters

trait ComponentFactory {
  def make(question: GUIQuestion): Component[_]
}

class QLComponentFactory(componentChangeListener: ComponentChangedListener, widgetFactory: WidgetFactory)
    extends ComponentFactory {

  // TODO Convert to decorators
  def make(question: GUIQuestion): Component[_] = {
    val component = makeComponent(question)
    component.addComponentChangedListener(componentChangeListener)
    component.setReadOnly(question.isReadOnly)
    question.component = Some(component)
    component
  }

  def makeComponent(question: GUIQuestion): Component[_] = {
    question.answerType match {
      case StringType =>
        StringComponent(question.id, new Label(question.label), widgetFactory.makeStringWidget(question))
      case BooleanType =>
        BooleanComponent(question.id, new Label(question.label), widgetFactory.makeBooleanWidget(question))
      case DateType =>
        DateComponent(question.id, new Label(question.label), widgetFactory.makeDateWidget(question))
      case IntegerType =>
        IntegerComponent(question.id, new Label(question.label), widgetFactory.makeIntegerWidget(question))
      case DecimalType =>
        DecimalComponent(question.id, new Label(question.label), widgetFactory.makeDecimalWidget(question))
      case MoneyType =>
        MoneyComponent(question.id, new Label(question.label), widgetFactory.makeMoneyWidget(question))
    }
  }
}

class QLSComponentFactory(componentChangeListener: ComponentChangedListener, widgetFactory: WidgetFactory)
    extends QLComponentFactory(componentChangeListener, widgetFactory) {
  override def make(question: GUIQuestion): Component[_] = {
    question match {
      case qlsQuestion: QLSGUIQuestion => {
        // TODO convert to decorators
        val component = super.make(question)
        qlsQuestion.styling.color.foreach(color => {
          JavaConverters
            .asScalaBuffer(component.getChildren)
            .foreach(child => child.setStyle(s"-fx-text-fill: ${color.color}; ${child.getStyle}"))
        })
        qlsQuestion.styling.width.foreach(
          width =>
            JavaConverters
              .asScalaBuffer(component.getChildren)
              .foreach(child => {
                child.setStyle(s"-fx-pref-width: ${width.width}px; ${child.getStyle}")
              }))
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

object QLComponentFactory {
  def apply(componentChangeListener: ComponentChangedListener) =
    new QLComponentFactory(componentChangeListener, new QLWidgetFactory())
}

object QLSComponentFactory {
  def apply(componentChangeListener: ComponentChangedListener) =
    new QLSComponentFactory(componentChangeListener, new QLSWidgetFactory())
}

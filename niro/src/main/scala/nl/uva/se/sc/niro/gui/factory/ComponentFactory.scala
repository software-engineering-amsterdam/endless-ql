package nl.uva.se.sc.niro.gui.factory

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.model.gui.GUIQuestion
import nl.uva.se.sc.niro.model.ql._

trait ComponentFactory {
  def make(question: GUIQuestion): Component[_]
}

class QLComponentFactory extends ComponentFactory {

  def make(question: GUIQuestion): Component[_] = {
    val component = makeComponent(question)
    component.setReadOnly(question.isReadOnly)
    question.component = Some(component)
    component
  }

  def makeComponent(question: GUIQuestion): Component[_] = {
    question.answerType match {
      case StringType =>
        StringComponent(question.id, new Label(question.label), QLWidgetFactory.makeStringWidget(question))
      case BooleanType =>
        BooleanComponent(question.id, new Label(question.label), QLWidgetFactory.makeBooleanWidget(question))
      case DateType =>
        DateComponent(question.id, new Label(question.label), QLWidgetFactory.makeDateWidget(question))
      case IntegerType =>
        IntegerComponent(question.id, new Label(question.label), QLWidgetFactory.makeIntegerWidget(question))
      case DecimalType =>
        DecimalComponent(question.id, new Label(question.label), QLWidgetFactory.makeDecimalWidget(question))
      case MoneyType =>
        MoneyComponent(question.id, new Label(question.label), QLWidgetFactory.makeMoneyWidget(question))
    }
  }
}

class QLSComponentFactory extends QLComponentFactory {

  override def makeComponent(question: GUIQuestion): Component[_] = {
    question.answerType match {
      case BooleanType =>
        BooleanComponent(question.id, new Label(question.label), QLSWidgetFactory.makeBooleanWidget(question))
      case _ => super.makeComponent(question)
    }
  }

}

object QLComponentFactory extends QLComponentFactory

object QLSComponentFactory extends QLSComponentFactory

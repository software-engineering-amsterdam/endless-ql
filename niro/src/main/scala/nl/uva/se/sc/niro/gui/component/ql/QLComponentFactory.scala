package nl.uva.se.sc.niro.gui.component.ql

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.component._
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.gui.widget._
import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion
import nl.uva.se.sc.niro.model.ql._

class QLComponentFactory(widgetFactory: WidgetFactory) extends ComponentFactory {

  def make(question: GUIQuestion): Component[_] = {
    question.answerType match {
      case StringType =>
        TextComponent(question.id, new Label(question.label), widgetFactory.makeStringWidget(question))
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

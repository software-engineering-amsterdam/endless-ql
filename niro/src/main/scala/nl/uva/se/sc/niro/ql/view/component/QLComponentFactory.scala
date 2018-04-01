package nl.uva.se.sc.niro.ql.view.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.ql.model.ast._
import nl.uva.se.sc.niro.ql.model.gui.Question
import nl.uva.se.sc.niro.ql.view.widget.WidgetFactory

class QLComponentFactory(widgetFactory: WidgetFactory) extends ComponentFactory {

  def make(question: Question): Component[_] = {
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

package nl.uva.se.sc.niro.ql.view.widget

import java.time.LocalDate

import nl.uva.se.sc.niro.ql.model.gui.Question

class QLWidgetFactory extends WidgetFactory {
  override def makeBooleanWidget(question: Question): Widget[java.lang.Boolean] = new BooleanField()
  override def makeDateWidget(question: Question): Widget[LocalDate] = new DateField()
  override def makeStringWidget(question: Question): Widget[String] = new TextField()
  override def makeIntegerWidget(question: Question): Widget[java.math.BigInteger] = new IntegerField()
  override def makeDecimalWidget(question: Question): Widget[java.math.BigDecimal] = new DecimalField()
  override def makeMoneyWidget(question: Question): Widget[java.math.BigDecimal] = new MoneyField()
}

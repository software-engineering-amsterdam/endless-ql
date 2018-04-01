package nl.uva.se.sc.niro.ql.view.widget

import java.time.LocalDate

import nl.uva.se.sc.niro.ql.model.gui.Question

class QLWidgetFactory extends WidgetFactory {
  override def makeBooleanWidget(question: Question): QLWidget[java.lang.Boolean] = new QLBooleanField()
  override def makeDateWidget(question: Question): QLWidget[LocalDate] = new QLDateField()
  override def makeStringWidget(question: Question): QLWidget[String] = new QLTextField()
  override def makeIntegerWidget(question: Question): QLWidget[java.math.BigInteger] = new QLIntegerField()
  override def makeDecimalWidget(question: Question): QLWidget[java.math.BigDecimal] = new QLDecimalField()
  override def makeMoneyWidget(question: Question): QLWidget[java.math.BigDecimal] = new QLMoneyField()
}

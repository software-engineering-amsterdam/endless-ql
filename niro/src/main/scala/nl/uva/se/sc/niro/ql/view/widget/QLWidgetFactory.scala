package nl.uva.se.sc.niro.ql.view.widget

import java.time.LocalDate

import nl.uva.se.sc.niro.ql.model.gui.GUIQuestion

class QLWidgetFactory extends WidgetFactory {
  override def makeBooleanWidget(question: GUIQuestion): QLWidget[java.lang.Boolean] = new QLBooleanField()
  override def makeDateWidget(question: GUIQuestion): QLWidget[LocalDate] = new QLDateField()
  override def makeStringWidget(question: GUIQuestion): QLWidget[String] = new QLTextField()
  override def makeIntegerWidget(question: GUIQuestion): QLWidget[java.math.BigInteger] = new QLIntegerField()
  override def makeDecimalWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLDecimalField()
  override def makeMoneyWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLMoneyField()
}
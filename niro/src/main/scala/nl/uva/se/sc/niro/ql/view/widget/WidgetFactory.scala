package nl.uva.se.sc.niro.ql.view.widget

import java.time.LocalDate

import nl.uva.se.sc.niro.ql.model.gui.Question

trait WidgetFactory {
  def makeBooleanWidget(question: Question): QLWidget[java.lang.Boolean]
  def makeDateWidget(question: Question): QLWidget[LocalDate]
  def makeStringWidget(question: Question): QLWidget[String]
  def makeIntegerWidget(question: Question): QLWidget[java.math.BigInteger]
  def makeDecimalWidget(question: Question): QLWidget[java.math.BigDecimal]
  def makeMoneyWidget(question: Question): QLWidget[java.math.BigDecimal]
}

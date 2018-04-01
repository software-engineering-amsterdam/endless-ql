package nl.uva.se.sc.niro.ql.view.widget

import java.time.LocalDate

import nl.uva.se.sc.niro.ql.model.gui.Question

trait WidgetFactory {
  def makeBooleanWidget(question: Question): Widget[java.lang.Boolean]
  def makeDateWidget(question: Question): Widget[LocalDate]
  def makeStringWidget(question: Question): Widget[String]
  def makeIntegerWidget(question: Question): Widget[java.math.BigInteger]
  def makeDecimalWidget(question: Question): Widget[java.math.BigDecimal]
  def makeMoneyWidget(question: Question): Widget[java.math.BigDecimal]
}

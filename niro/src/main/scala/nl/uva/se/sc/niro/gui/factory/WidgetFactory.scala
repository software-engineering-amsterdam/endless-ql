package nl.uva.se.sc.niro.gui.factory

import java.time.LocalDate

import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.model.gui.GUIQuestion

trait WidgetFactory {
  val INTEGER_MASK = "\\d*"
  val DECIMAL_MASK = "\\d*(\\.\\d*)?"
  val MONEY_MASK = "\\d*(\\.\\d{0,2})?"
  val DATE_FORMAT = "yyyy-MM-dd"
  def makeBooleanWidget(question: GUIQuestion): QLWidget[Boolean]
  def makeDateWidget(question: GUIQuestion): QLWidget[LocalDate]
  def makeStringWidget(question: GUIQuestion): QLWidget[String]
  def makeIntegerWidget(question: GUIQuestion): QLWidget[java.lang.Integer]
  def makeDecimalWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal]
  def makeMoneyWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal]
}

object QLWidgetFactory extends WidgetFactory {
  override def makeBooleanWidget(question: GUIQuestion) = new QLBooleanField()
  override def makeDateWidget(question: GUIQuestion): QLWidget[LocalDate] = new QLDateField()
  override def makeStringWidget(question: GUIQuestion): QLWidget[String] = new QLTextField()
  override def makeIntegerWidget(question: GUIQuestion): QLWidget[java.lang.Integer] = new QLIntegerField()
  override def makeDecimalWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLDecimalField()
  override def makeMoneyWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLMoneyField()
}

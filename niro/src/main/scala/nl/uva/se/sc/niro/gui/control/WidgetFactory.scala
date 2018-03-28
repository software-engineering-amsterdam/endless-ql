package nl.uva.se.sc.niro.gui.control

import java.time.LocalDate

import nl.uva.se.sc.niro.gui.control.ql.QLWidget
import nl.uva.se.sc.niro.model.gui._

trait WidgetFactory {
  def makeBooleanWidget(question: GUIQuestion): QLWidget[Boolean]
  def makeDateWidget(question: GUIQuestion): QLWidget[LocalDate]
  def makeStringWidget(question: GUIQuestion): QLWidget[String]
  def makeIntegerWidget(question: GUIQuestion): QLWidget[java.math.BigInteger]
  def makeDecimalWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal]
  def makeMoneyWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal]
}

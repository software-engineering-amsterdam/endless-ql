package nl.uva.se.sc.niro.gui.factory

import java.time.LocalDate

import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.model.gui.{ ComboBox, GUIQuestion, QLSGUIQuestion, Radio }

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

class QLWidgetFactory extends WidgetFactory {
  override def makeBooleanWidget(question: GUIQuestion): QLWidget[Boolean] = new QLBooleanField()
  override def makeDateWidget(question: GUIQuestion): QLWidget[LocalDate] = new QLDateField()
  override def makeStringWidget(question: GUIQuestion): QLWidget[String] = new QLTextField()
  override def makeIntegerWidget(question: GUIQuestion): QLWidget[java.lang.Integer] = new QLIntegerField()
  override def makeDecimalWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLDecimalField()
  override def makeMoneyWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal] = new QLMoneyField()
}

class QLSWidgetFactory extends QLWidgetFactory {
  override def makeBooleanWidget(question: GUIQuestion): QLWidget[Boolean] = question match {
    case QLSGUIQuestion(_, _, _, _, _, _ @Some(ComboBox(trueLabel, falseLabel))) =>
      new QLComboBooleanField(trueLabel, falseLabel)
    case QLSGUIQuestion(_, _, _, _, _, _ @Some(Radio(trueLabel, falseLabel))) =>
      new QLRadioBooleanField(trueLabel, falseLabel)
    case _ =>
      super.makeBooleanWidget(question)
  }
}

object QLWidgetFactory extends QLWidgetFactory

object QLSWidgetFactory extends QLSWidgetFactory

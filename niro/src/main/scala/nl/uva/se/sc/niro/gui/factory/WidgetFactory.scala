package nl.uva.se.sc.niro.gui.factory

import java.time.LocalDate

import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.model.gui._

trait WidgetFactory {
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
    case qlsQuestion: QLSGUIQuestion => qlsQuestion.styling.widgetStyle match {
      case Some(GUIComboBoxStyle(trueLabel, falseLabel)) => new QLSComboBooleanField(trueLabel, falseLabel)
      case Some(GUIRadioStyle(trueLabel, falseLabel))    => new QLSRadioBooleanField(trueLabel, falseLabel)
      case _                                             => super.makeBooleanWidget(question)
    }
    case _ =>
      super.makeBooleanWidget(question)
  }

  override def makeIntegerWidget(question: GUIQuestion): QLWidget[Integer] = question match {
    case _ => super.makeIntegerWidget(question)
    case qlsQuestion: QLSGUIQuestion => qlsQuestion.styling.widgetStyle match {
      case Some(GUISpinBoxStyle()) => new QLSIntegerSpinField()
      case _                       => super.makeIntegerWidget(question)
    }
    case _ =>
      super.makeIntegerWidget(question)
  }

}

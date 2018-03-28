package nl.uva.se.sc.niro.gui.widget.qls

import nl.uva.se.sc.niro.gui.widget.ql.{ QLWidget, QLWidgetFactory }
import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion
import nl.uva.se.sc.niro.model.gui.qls.QLSGUIQuestion
import nl.uva.se.sc.niro.model.gui.qls.style.{ GUIComboBoxStyle, GUIRadioStyle, GUISpinBoxStyle }

class QLSWidgetFactory extends QLWidgetFactory {

  override def makeBooleanWidget(question: GUIQuestion): QLWidget[Boolean] = question match {
    case QLSGUIQuestion(_, _, _, _, _, styling) =>
      styling.widgetStyle match {
        case Some(GUIComboBoxStyle(trueLabel, falseLabel)) => new QLSBooleanComboField(trueLabel, falseLabel)
        case Some(GUIRadioStyle(trueLabel, falseLabel))    => new QLSBooleanRadioField(trueLabel, falseLabel)
        case _                                             => super.makeBooleanWidget(question)
      }
    case _ => super.makeBooleanWidget(question)
  }

  override def makeIntegerWidget(question: GUIQuestion): QLWidget[java.math.BigInteger] = question match {
    case QLSGUIQuestion(_, _, _, _, _, styling) =>
      styling.widgetStyle match {
        case Some(GUISpinBoxStyle()) => new QLSIntegerSpinField()
        case _                       => super.makeIntegerWidget(question)
      }
    case _ => super.makeIntegerWidget(question)
  }

  override def makeDecimalWidget(question: GUIQuestion): QLWidget[java.math.BigDecimal] = question match {
    case QLSGUIQuestion(_, _, _, _, _, styling) =>
      styling.widgetStyle match {
        case Some(GUISpinBoxStyle()) => new QLSDecimalSpinField()
        case _                       => super.makeDecimalWidget(question)
      }
    case _ => super.makeDecimalWidget(question)
  }
}

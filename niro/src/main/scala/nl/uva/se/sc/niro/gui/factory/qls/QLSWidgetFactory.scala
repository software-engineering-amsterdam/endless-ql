package nl.uva.se.sc.niro.gui.factory.qls

import nl.uva.se.sc.niro.gui.control.ql.QLWidget
import nl.uva.se.sc.niro.gui.control.qls.{ QLSBooleanComboField, QLSBooleanRadioField, QLSIntegerSpinField }
import nl.uva.se.sc.niro.gui.factory.ql.QLWidgetFactory
import nl.uva.se.sc.niro.model.gui._

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

}

package nl.uva.se.sc.niro.qls.view.widget

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.scene.control.Spinner
import nl.uva.se.sc.niro.ql.model.gui.Question
import nl.uva.se.sc.niro.ql.view.widget.{ Widget, QLWidgetFactory }
import nl.uva.se.sc.niro.qls.model.gui.QLSQuestion
import nl.uva.se.sc.niro.qls.model.gui.style.{ ComboBox, Radio, Slider, SpinBox }

class QLSWidgetFactory extends QLWidgetFactory {

  override def makeBooleanWidget(question: Question): Widget[java.lang.Boolean] = question match {
    case QLSQuestion(_, _, _, _, _, styling) =>
      styling.widgetStyle match {
        case Some(ComboBox(trueLabel, falseLabel)) => new BooleanComboField(trueLabel, falseLabel)
        case Some(Radio(trueLabel, falseLabel))    => new BooleanRadioField(trueLabel, falseLabel)
        case _                                     => super.makeBooleanWidget(question)
      }
    case _ => super.makeBooleanWidget(question)
  }

  override def makeIntegerWidget(question: Question): Widget[java.math.BigInteger] = question match {
    case QLSQuestion(_, _, _, isReadOnly, _, styling) =>
      styling.widgetStyle match {
        case Some(SpinBox(minimum, maximum, stepSize)) =>
          if (isReadOnly) super.makeIntegerWidget(question)
          else new IntegerSpinField(minimum.toInt, maximum.toInt, stepSize.toInt)
        case Some(Slider(minimum, maximum)) =>
          if (isReadOnly) super.makeIntegerWidget(question) else new IntegerSliderField(minimum, maximum)
        case _ => super.makeIntegerWidget(question)
      }
    case _ => super.makeIntegerWidget(question)
  }

  override def makeDecimalWidget(question: Question): Widget[java.math.BigDecimal] = question match {
    case QLSQuestion(_, _, _, isReadOnly, _, styling) =>
      styling.widgetStyle match {
        case Some(SpinBox(minimum, maximum, stepSize)) =>
          if (isReadOnly) super.makeDecimalWidget(question) else new DecimalSpinField(minimum, maximum, stepSize)
        case Some(Slider(minimum, maximum)) =>
          if (isReadOnly) super.makeDecimalWidget(question) else new DecimalSliderField(minimum, maximum)
        case _ => super.makeDecimalWidget(question)
      }
    case _ => super.makeDecimalWidget(question)
  }

  override def makeMoneyWidget(question: Question): Widget[java.math.BigDecimal] = question match {
    case QLSQuestion(_, _, _, isReadOnly, _, styling) =>
      styling.widgetStyle match {
        case Some(SpinBox(minimum, maximum, stepSize)) =>
          if (isReadOnly) super.makeMoneyWidget(question) else new MoneySpinField(minimum, maximum, stepSize)
        case Some(Slider(minimum, maximum)) =>
          if (isReadOnly) super.makeMoneyWidget(question) else new MoneySliderField(minimum, maximum)
        case _ => super.makeMoneyWidget(question)
      }
    case _ => super.makeMoneyWidget(question)
  }

  type SpinnerWidget[T] = Spinner[T] with Widget[T]
  def addValueChangedListener[T](widget: SpinnerWidget[T]): Unit = {
    widget
      .focusedProperty()
      .addListener(new ChangeListener[java.lang.Boolean] {
        override def changed(
            observable: ObservableValue[_ <: java.lang.Boolean],
            oldValue: java.lang.Boolean,
            newValue: java.lang.Boolean): Unit = {
          if (!newValue) {
            widget.increment(0)
            widget.valueChanged()
          }
        }
      })

  }
}

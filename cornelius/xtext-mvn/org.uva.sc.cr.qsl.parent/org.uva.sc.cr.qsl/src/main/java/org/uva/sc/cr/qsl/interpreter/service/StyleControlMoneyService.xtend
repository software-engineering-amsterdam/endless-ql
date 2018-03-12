package org.uva.sc.cr.qsl.interpreter.service

import java.text.NumberFormat
import javafx.beans.binding.Bindings
import javafx.scene.control.Slider
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory
import javafx.scene.control.TextFormatter
import javafx.util.converter.DoubleStringConverter
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperMoney
import org.uva.sc.cr.qsl.qSL.DefaultStyle
import org.uva.sc.cr.qsl.qSL.Widget
import org.uva.sc.cr.qsl.qSL.WidgetSlider
import org.uva.sc.cr.qsl.qSL.WidgetSpinbox

@Singleton
class StyleControlMoneyService extends StyleControlService {

	def dispatch styleMoney(ControlWrapperMoney controlWrapperMoney, Widget widgetSpinbox,
		DefaultStyle defaultStyleToApply) {
		return styleDefaultControl(controlWrapperMoney, defaultStyleToApply)
	}

	def dispatch styleMoney(ControlWrapperMoney controlWrapperMoney, WidgetSpinbox widgetSpinbox,
		DefaultStyle defaultStyleToApply) {

		val spinner = new Spinner()
		spinner.valueFactory = new DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0, 0.01)
		spinner.editor.textFormatter = new TextFormatter(new DoubleStringConverter)
		spinner.editable = true

		val textField = controlWrapperMoney.textField
		Bindings.bindBidirectional(spinner.editor.textProperty, textField.textProperty)
		copyControlConfiguration(textField, spinner)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperMoney.label, spinner)
	}

	def dispatch styleMoney(ControlWrapperMoney controlWrapperMoney, WidgetSlider widgetSlider,
		DefaultStyle defaultStyleToApply) {

		val slider = new Slider()

		val textField = controlWrapperMoney.textField
		Bindings.bindBidirectional(textField.textProperty, slider.valueProperty, NumberFormat.getNumberInstance())
		copyControlConfiguration(textField, slider)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperMoney.label, slider)
	}

}

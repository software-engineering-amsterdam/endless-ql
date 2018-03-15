package org.uva.sc.cr.qsl.interpreter.service

import java.text.NumberFormat
import javafx.beans.binding.Bindings
import javafx.scene.control.Slider
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory
import javafx.scene.control.TextFormatter
import javafx.util.converter.DoubleStringConverter
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperDecimal
import org.uva.sc.cr.qsl.qSL.DefaultStyle
import org.uva.sc.cr.qsl.qSL.Widget
import org.uva.sc.cr.qsl.qSL.WidgetSlider
import org.uva.sc.cr.qsl.qSL.WidgetSpinbox

@Singleton
class StyleControlDecimalService extends StyleControlService {

	private static val SPINNER_MIN_VALUE = Double.MIN_VALUE
	private static val SPINNER_MAX_VALUE = Double.MAX_VALUE
	private static val SPINNER_INITIAL_VALUE = 0
	private static val SPINNER_STEP_VALUE = 0.001

	private static val SLIDER_MAX_VALUE = 5000

	def dispatch styleDecimal(ControlWrapperDecimal controlWrapperDecimal, Widget widgetSpinbox,
		DefaultStyle defaultStyleToApply) {
		return styleDefaultControl(controlWrapperDecimal, defaultStyleToApply)
	}

	def dispatch styleDecimal(ControlWrapperDecimal controlWrapperDecimal, WidgetSpinbox widgetSpinbox,
		DefaultStyle defaultStyleToApply) {

		val spinner = new Spinner()
		spinner.valueFactory = new DoubleSpinnerValueFactory(SPINNER_MIN_VALUE, SPINNER_MAX_VALUE,
			SPINNER_INITIAL_VALUE, SPINNER_STEP_VALUE)
		spinner.editor.textFormatter = new TextFormatter(new DoubleStringConverter)
		spinner.editable = true

		val textField = controlWrapperDecimal.textField
		Bindings.bindBidirectional(spinner.editor.textProperty, textField.textProperty)
		copyControlConfiguration(textField, spinner)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperDecimal.label, spinner)
	}

	def dispatch styleDecimal(ControlWrapperDecimal controlWrapperDecimal, WidgetSlider widgetSlider,
		DefaultStyle defaultStyleToApply) {

		val slider = new Slider()
		slider.max = SLIDER_MAX_VALUE

		val textField = controlWrapperDecimal.textField
		Bindings.bindBidirectional(textField.textProperty, slider.valueProperty, NumberFormat.numberInstance)
		copyControlConfiguration(textField, slider)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperDecimal.label, slider)
	}
}

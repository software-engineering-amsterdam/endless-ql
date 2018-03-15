package org.uva.sc.cr.qsl.interpreter.service

import java.text.NumberFormat
import javafx.beans.binding.Bindings
import javafx.scene.control.Slider
import javafx.scene.control.Spinner
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory
import javafx.scene.control.TextFormatter
import javafx.util.converter.IntegerStringConverter
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperInteger
import org.uva.sc.cr.qsl.qSL.DefaultStyle
import org.uva.sc.cr.qsl.qSL.Widget
import org.uva.sc.cr.qsl.qSL.WidgetSlider
import org.uva.sc.cr.qsl.qSL.WidgetSpinbox

@Singleton
class StyleControlIntegerService extends StyleControlService {

	private static val SPINNER_MIN_VALUE = Integer.MIN_VALUE
	private static val SPINNER_MAX_VALUE = Integer.MAX_VALUE
	private static val SPINNER_INITIAL_VALUE = 0
	private static val SPINNER_STEP_VALUE = 1

	private static val SLIDER_MAX_VALUE = 100

	def dispatch styleInteger(ControlWrapperInteger controlWrapperInteger, Widget widgetSpinbox,
		DefaultStyle defaultStyleToApply) {
		return styleDefaultControl(controlWrapperInteger, defaultStyleToApply)
	}

	def dispatch styleInteger(ControlWrapperInteger controlWrapperInteger, WidgetSpinbox widgetSpinbox,
		DefaultStyle defaultStyleToApply) {

		val spinner = new Spinner()
		spinner.valueFactory = new IntegerSpinnerValueFactory(SPINNER_MIN_VALUE, SPINNER_MAX_VALUE,
			SPINNER_INITIAL_VALUE, SPINNER_STEP_VALUE)
		spinner.editor.textFormatter = new TextFormatter(new IntegerStringConverter)
		spinner.editable = true

		val textField = controlWrapperInteger.textField
		Bindings.bindBidirectional(spinner.editor.textProperty, textField.textProperty)
		copyControlConfiguration(textField, spinner)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperInteger.label, spinner)
	}

	def dispatch styleInteger(ControlWrapperInteger controlWrapperInteger, WidgetSlider widgetSlider,
		DefaultStyle defaultStyleToApply) {

		val slider = new Slider()
		slider.max = SLIDER_MAX_VALUE

		val textField = controlWrapperInteger.textField
		Bindings.bindBidirectional(textField.textProperty, slider.valueProperty, NumberFormat.integerInstance)
		copyControlConfiguration(textField, slider)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperInteger.label, slider)
	}

}

package org.uva.sc.cr.qsl.interpreter.service

import javafx.beans.binding.Bindings
import javafx.scene.control.ChoiceBox
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.util.StringConverter
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperBoolean
import org.uva.sc.cr.qsl.qSL.DefaultStyle
import org.uva.sc.cr.qsl.qSL.Widget
import org.uva.sc.cr.qsl.qSL.WidgetDropdown
import org.uva.sc.cr.qsl.qSL.WidgetRadio

@Singleton
class StyleControlBooleanService extends StyleControlService {

	def dispatch styleBoolean(ControlWrapperBoolean controlWrapperBoolean, Widget widgetRadio,
		DefaultStyle defaultStyleToApply) {
		return styleDefaultControl(controlWrapperBoolean, defaultStyleToApply)
	}

	def dispatch styleBoolean(ControlWrapperBoolean controlWrapperBoolean, WidgetRadio widgetRadio,
		DefaultStyle defaultStyleToApply) {

		val group = new ToggleGroup();
		val radioButton1 = new RadioButton(widgetRadio.option1)
		radioButton1.toggleGroup = group
		val radioButton2 = new RadioButton(widgetRadio.option2)
		radioButton2.toggleGroup = group

		val checkBox = controlWrapperBoolean.checkBox
		Bindings.bindBidirectional(radioButton1.selectedProperty, checkBox.selectedProperty)

		copyControlConfiguration(checkBox, radioButton1)
		copyControlConfiguration(checkBox, radioButton2)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperBoolean.label, radioButton1,
			radioButton2)
	}

	def dispatch styleBoolean(ControlWrapperBoolean controlWrapperBoolean, WidgetDropdown widgetDropdown,
		DefaultStyle defaultStyleToApply) {

		val choiceBox = new ChoiceBox
		choiceBox.items.add(widgetDropdown.option1)
		choiceBox.items.add(widgetDropdown.option2)

		val checkBox = controlWrapperBoolean.checkBox
		Bindings.bindBidirectional(choiceBox.valueProperty, checkBox.selectedProperty, new StringConverter<Boolean>() {

			override fromString(String arg0) {
				if (arg0 == widgetDropdown.option1) {
					return true
				} else {
					return false
				}
			}

			override toString(Boolean arg0) {
				if (arg0 == true) {
					return widgetDropdown.option1
				} else {
					return widgetDropdown.option2
				}
			}

		})
		copyControlConfiguration(checkBox, choiceBox)

		return buildHBoxAndApplyDefaultStyle(defaultStyleToApply, controlWrapperBoolean.label, choiceBox)
	}

}

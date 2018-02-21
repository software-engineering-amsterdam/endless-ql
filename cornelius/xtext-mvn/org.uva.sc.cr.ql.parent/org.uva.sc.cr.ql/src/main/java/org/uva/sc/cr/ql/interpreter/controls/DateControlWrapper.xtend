package org.uva.sc.cr.ql.interpreter.controls

import javafx.beans.binding.BooleanBinding
import javafx.scene.control.DatePicker
import org.uva.sc.cr.ql.qL.Question

class DateControlWrapper extends ControlWrapper {

	private val DatePicker control

	new(Question question, BooleanBinding binding) {
		super(question, binding)
		control = new DatePicker
		setDefaults
	}

	override getValue() {
		return control.value
	}

	override getControl() {
		return control
	}

}

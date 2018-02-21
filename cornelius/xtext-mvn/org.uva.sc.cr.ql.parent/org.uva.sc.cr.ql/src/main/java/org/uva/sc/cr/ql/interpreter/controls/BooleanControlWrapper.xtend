package org.uva.sc.cr.ql.interpreter.controls

import javafx.beans.binding.BooleanBinding
import javafx.scene.control.CheckBox
import org.uva.sc.cr.ql.qL.Question

class BooleanControlWrapper extends ControlWrapper {

	private val CheckBox control

	new(Question question, BooleanBinding binding) {
		super(question, binding)
		control = new CheckBox
		if (question.expression !== null) {
			control.selectedProperty.bind(binding)
		}
		setDefaults
	}

	override getValue() {
		return control.selected
	}

	override getControl() {
		return control
	}

}

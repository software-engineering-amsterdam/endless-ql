package org.uva.sc.cr.ql.interpreter.controls

import javafx.beans.binding.StringBinding
import javafx.scene.control.TextField
import org.uva.sc.cr.ql.qL.Question

class TextControlWrapper extends ControlWrapper {

	private val TextField control

	new(Question question, StringBinding binding) {
		super(question, binding)
		control = new TextField
		if (question.expression !== null) {
			control.textProperty.bind(binding)
		}
		setDefaults
	}

	override getValue() {
		return control.text
	}

	override getControl() {
		return control
	}

}

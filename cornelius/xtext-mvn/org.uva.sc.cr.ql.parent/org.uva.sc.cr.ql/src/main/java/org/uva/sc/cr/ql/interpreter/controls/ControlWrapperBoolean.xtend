package org.uva.sc.cr.ql.interpreter.controls

import javafx.beans.binding.BooleanBinding
import javafx.scene.control.CheckBox
import org.uva.sc.cr.ql.qL.Question
import org.eclipse.xtend.lib.annotations.Accessors

class ControlWrapperBoolean extends ControlWrapper {

	@Accessors(PUBLIC_GETTER)
	private var CheckBox checkBox

	new(Question question, BooleanBinding binding) {
		super(question, binding)
		if (question.expression !== null) {
			checkBox.selectedProperty.bind(binding)
		}
	}

	override getValue() {
		return checkBox.selected
	}

	override getControl() {
		return checkBox
	}

	override protected buildControl() {
		checkBox = new CheckBox
	}

}

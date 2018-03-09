package org.uva.sc.cr.ql.interpreter.controls

import javafx.beans.binding.StringBinding
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.util.converter.BigDecimalStringConverter
import org.uva.sc.cr.ql.qL.Question
import org.eclipse.xtend.lib.annotations.Accessors

class ControlWrapperMoney extends ControlWrapper {

	@Accessors(PUBLIC_GETTER)
	private var TextField textField

	new(Question question, StringBinding binding) {
		super(question, binding)
		if (question.expression !== null) {
			textField.textProperty.bind(binding)
		}
	}

	override getValue() {
		return textField.text
	}

	override getControl() {
		return textField
	}

	override protected buildControl() {
		textField = new TextField
		textField.textFormatter = new TextFormatter(new BigDecimalStringConverter)
	}

}

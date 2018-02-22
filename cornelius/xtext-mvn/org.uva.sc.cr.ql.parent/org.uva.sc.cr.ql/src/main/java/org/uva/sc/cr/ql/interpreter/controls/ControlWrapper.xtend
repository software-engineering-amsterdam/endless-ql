package org.uva.sc.cr.ql.interpreter.controls

import javafx.beans.binding.Binding
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.scene.control.Control
import org.eclipse.xtend.lib.annotations.Accessors
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.Question

abstract class ControlWrapper {

	@Accessors(PUBLIC_GETTER)
	val String name

	val Expression expression

	new(Question question, Binding binding) {
		this.name = question.name
		this.expression = question.expression
	}

	def Object getValue()

	def Control getControl()

	def void registerListener(EventHandler eventHandler) {
		control.addEventHandler(EventType.ROOT, eventHandler)
	}

	protected def setDefaults() {
		control.id = name
		if (expression !== null) {
			control.disable = true
		}
	}

}

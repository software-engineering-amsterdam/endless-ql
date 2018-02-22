package org.uva.sc.cr.ql.interpreter.controls

import javafx.beans.binding.Binding
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.scene.control.Control
import org.eclipse.xtend.lib.annotations.Accessors
import org.uva.sc.cr.ql.qL.Question
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.Node

abstract class ControlWrapper {

	@Accessors(PUBLIC_GETTER)
	val String name

	val Label label

	new(Question question, Binding binding) {
		this.name = question.name
		this.label = new Label(question.label)
		buildControl()
		control.id = name
		if (question.expression !== null) {
			control.disable = true
		}
	}
	
	def protected void buildControl()

	def Object getValue()

	def Control getControl()

	def void registerListener(EventHandler eventHandler) {
		control.addEventHandler(EventType.ROOT, eventHandler)
	}
	
	def Node getControlWithLabel(){
		val hbox = new HBox
		hbox.children.add(label)
		hbox.children.add(control)
		return hbox
	}

}

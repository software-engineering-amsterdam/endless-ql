package org.uva.sc.pc.ql.interpreter

import java.util.ArrayList
import java.util.HashMap
import javafx.beans.binding.Binding
import javafx.event.Event
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.scene.control.CheckBox
import javafx.scene.control.Control
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javax.inject.Inject
import org.uva.sc.pc.ql.qLang.Expression
import org.uva.sc.pc.ql.qLang.Form
import org.uva.sc.pc.ql.qLang.Question
import org.uva.sc.pc.ql.qLang.TypeBool
import org.uva.sc.pc.ql.qLang.TypeDate
import org.uva.sc.pc.ql.qLang.TypeDecimal
import org.uva.sc.pc.ql.qLang.TypeInteger
import org.uva.sc.pc.ql.qLang.TypeMoney
import org.uva.sc.pc.ql.qLang.TypeString
import org.uva.sc.pc.ql.qLang.util.MissingCaseException

class StageService {

	private val controls = new HashMap<Object, Control>

	private val bindings = new ArrayList<Binding>

	@Inject
	private var BindingService bindingService

	def buildGuiLayout(Form form) {
		val root = new VBox
		form.body.questions.forEach [
			val control = buildControlForQuestion(it)
			root.children.add(control)
		]
		form.blocks.forEach [
			val box = new VBox();
			it.body.questions.forEach [
				val control = buildControlForQuestion(it)
				box.children.add(control)
			]
			val binding = bindingService.buildBindingForTypeBoolean(controls, it.expression)
			box.visibleProperty.bind(binding)
			bindings.add(binding)
			root.children.add(box)
		]
		registerListeners
		return root
	}

	def private buildControlForQuestion(Question question) {
		val hbox = new HBox
		hbox.children.add(new Label(question.label))
		var Control control
		switch question.type {
			TypeBool:
				control = buildControlForTypeBoolean(question.expression)
			TypeString:
				control = buildControlForTypeString(question.expression)
			TypeInteger:
				control = buildControlForTypeInteger(question.expression)
			TypeDecimal,
			TypeMoney:
				control = buildControlForTypeDecimalAndMoney(question.expression)
			TypeDate:
				control = new DatePicker
			default:
				throw new MissingCaseException
		}
		if (question.expression !== null) {
			control.disable = true
		}
		hbox.children.add(control)
		controls.put(question.name, control)
		return hbox
	}

	def private buildControlForTypeBoolean(Expression expression) {
		var text = new CheckBox
		if (expression !== null) {
			val binding = bindingService.buildBindingForTypeBoolean(controls, expression)
			text.selectedProperty.bind(binding)
			bindings.add(binding)
		}
		return text
	}

	def private buildControlForTypeString(Expression expression) {
		var text = new TextField
		if (expression !== null) {
			val binding = bindingService.buildBindingForTypeString(controls, expression)
			text.textProperty.bind(binding)
			bindings.add(binding)
		}
		return text
	}

	def private buildControlForTypeInteger(Expression expression) {
		var text = new TextField
		if (expression !== null) {
			val binding = bindingService.buildBindingForTypeInteger(controls, expression)
			text.textProperty.bind(binding)
			bindings.add(binding)
		}
		return text
	}

	def private buildControlForTypeDecimalAndMoney(Expression expression) {
		var text = new TextField
		if (expression !== null) {
			val binding = bindingService.buildBindingForTypeDecimalAndMoney(controls, expression)
			text.textProperty.bind(binding)
			bindings.add(binding)
		}
		return text
	}

	def private registerListeners() {
		controls.forEach [ name, control |
			control.addEventHandler(EventType.ROOT, new EventHandler() {
				override handle(Event arg0) {
					invalidateBindings
				}
			})
		]
	}

	def private invalidateBindings() {
		bindings.forEach [
			it.invalidate()
		]
	}
}

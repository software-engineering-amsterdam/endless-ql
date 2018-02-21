package org.uva.sc.cr.ql.interpreter

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
import org.uva.sc.cr.ql.qL.Block
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.Form
import org.uva.sc.cr.ql.qL.Question
import org.uva.sc.cr.ql.qL.TypeBool
import org.uva.sc.cr.ql.qL.TypeDate
import org.uva.sc.cr.ql.qL.TypeDecimal
import org.uva.sc.cr.ql.qL.TypeInteger
import org.uva.sc.cr.ql.qL.TypeMoney
import org.uva.sc.cr.ql.qL.TypeString
import org.uva.sc.cr.ql.util.MissingCaseException

class StageService {

	private val controls = new HashMap<String, Control>

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
			val block = buildControlForBlock(it)
			root.children.add(block)
		]
		root.lookup("hasSoldHouse")
		return root
	}

	def private buildControlForBlock(Block block) {
		val box = new VBox();
		block.body.questions.forEach [
			val control = buildControlForQuestion(it)
			box.children.add(control)
		]
		val binding = bindingService.buildBindingForTypeBoolean(controls, block.expression)
		box.visibleProperty.bind(binding)
		bindings.add(binding)
		return box
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
		setControlDefaults(control, question)
		hbox.children.add(control)
		controls.put(question.name, control)
		return hbox
	}
	
	private def setControlDefaults(Control control, Question question) {
		control.id = question.name
		if (question.expression !== null) {
			control.disable = true
		}
		registerListener(control)
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

	def private registerListener(Control control) {
		control.addEventHandler(EventType.ROOT, new EventHandler() {
			override handle(Event arg0) {
				invalidateBindings
			}
		})
	}

	def private invalidateBindings() {
		bindings.forEach [
			it.invalidate()
		]
	}
}

package org.uva.sc.cr.ql.interpreter.service

import java.util.ArrayList
import java.util.List
import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javax.inject.Inject
import javax.inject.Singleton
import org.eclipse.xtend.lib.annotations.Accessors
import org.uva.sc.cr.ql.interpreter.controls.BooleanControlWrapper
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapper
import org.uva.sc.cr.ql.interpreter.controls.DateControlWrapper
import org.uva.sc.cr.ql.interpreter.controls.DecimalControlWrapper
import org.uva.sc.cr.ql.interpreter.controls.IntegerControlWrapper
import org.uva.sc.cr.ql.interpreter.controls.TextControlWrapper
import org.uva.sc.cr.ql.qL.Question
import org.uva.sc.cr.ql.qL.TypeBool
import org.uva.sc.cr.ql.qL.TypeDate
import org.uva.sc.cr.ql.qL.TypeDecimal
import org.uva.sc.cr.ql.qL.TypeInteger
import org.uva.sc.cr.ql.qL.TypeMoney
import org.uva.sc.cr.ql.qL.TypeString
import org.uva.sc.cr.ql.util.MissingCaseException

@Singleton
class ControlService {

	@Inject
	private var BindingService bindingService

	@Accessors(PUBLIC_GETTER)
	private val List<ControlWrapper> controls

	new() {
		controls = new ArrayList<ControlWrapper>
	}

	def public buildControlForQuestion(Question question) {
		val hbox = new HBox
		hbox.children.add(new Label(question.label))
		var ControlWrapper controlWrapper
		switch question.type {
			TypeBool:
				controlWrapper = buildControlForTypeBoolean(question)
			TypeString:
				controlWrapper = buildControlForTypeString(question)
			TypeInteger:
				controlWrapper = buildControlForTypeInteger(question)
			TypeDecimal,
			TypeMoney:
				controlWrapper = buildControlForTypeDecimalAndMoney(question)
			TypeDate:
				controlWrapper = buildControlForTypeDate(question)
			default:
				throw new MissingCaseException
		}
		hbox.children.add(controlWrapper.control)
		controlWrapper.registerListener(buildEventHandler)
		controls.add(controlWrapper)
		return hbox
	}

	def private buildControlForTypeBoolean(Question question) {
		val binding = bindingService.buildBindingForTypeBoolean(controls, question.expression)
		return new BooleanControlWrapper(question, binding)
	}

	def private buildControlForTypeString(Question question) {
		val binding = bindingService.buildBindingForTypeString(controls, question.expression)
		return new TextControlWrapper(question, binding)
	}

	def private buildControlForTypeInteger(Question question) {
		val binding = bindingService.buildBindingForTypeInteger(controls, question.expression)
		return new IntegerControlWrapper(question, binding)
	}

	def private buildControlForTypeDecimalAndMoney(Question question) {
		val binding = bindingService.buildBindingForTypeDecimalAndMoney(controls, question.expression)
		return new DecimalControlWrapper(question, binding)
	}

	def buildControlForTypeDate(Question question) {
		return new DateControlWrapper(question, null)
	}

	def private buildEventHandler() {
		new EventHandler() {
			override handle(Event arg0) {
				bindingService.invalidateBindings
			}
		}
	}

}

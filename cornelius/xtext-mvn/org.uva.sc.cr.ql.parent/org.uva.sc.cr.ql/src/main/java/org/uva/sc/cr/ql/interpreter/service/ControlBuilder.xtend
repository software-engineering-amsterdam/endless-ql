package org.uva.sc.cr.ql.interpreter.service

import java.util.ArrayList
import java.util.List
import javafx.event.Event
import javafx.event.EventHandler
import javax.inject.Inject
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapper
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.Question
import org.uva.sc.cr.ql.qL.QuestionType
import org.uva.sc.cr.ql.util.MissingCaseException
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperBoolean
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperDate
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperDecimal
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperInteger
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperMoney
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperText

@Singleton
class ControlBuilder {

	@Inject
	private var BindingBuilder bindingBuilder

	private val List<ControlWrapper> controls

	new() {
		controls = new ArrayList<ControlWrapper>()
	}

	def public buildControlForQuestion(Question question, Expression visibleExpression) {
		var ControlWrapper controlWrapper
		switch question.type {
			case QuestionType.TYPE_BOOLEAN:
				controlWrapper = buildControlForTypeBoolean(question)
			case QuestionType.TYPE_STRING:
				controlWrapper = buildControlForTypeString(question)
			case QuestionType.TYPE_INTEGER:
				controlWrapper = buildControlForTypeInteger(question)
			case QuestionType.TYPE_DECIMAL:
				controlWrapper = buildControlForTypeDecimal(question)
			case QuestionType.TYPE_MONEY:
				controlWrapper = buildControlForTypeMoney(question)
			case QuestionType.TYPE_DATE:
				controlWrapper = buildControlForTypeDate(question)
			default:
				throw new MissingCaseException()
		}
		controlWrapper.registerListener(buildEventHandler())
		if (visibleExpression !== null) {
			val binding = bindingBuilder.buildBindingForTypeBoolean(controls, visibleExpression)
			controlWrapper.bindVisibleProperty(binding)
		}
		controls.add(controlWrapper)
		return controlWrapper.controlWithLabel
	}

	def private buildControlForTypeBoolean(Question question) {
		val binding = bindingBuilder.buildBindingForTypeBoolean(controls, question.expression)
		return new ControlWrapperBoolean(question, binding)
	}

	def private buildControlForTypeString(Question question) {
		val binding = bindingBuilder.buildBindingForTypeString(controls, question.expression)
		return new ControlWrapperText(question, binding)
	}

	def private buildControlForTypeInteger(Question question) {
		val binding = bindingBuilder.buildBindingForTypeInteger(controls, question.expression)
		return new ControlWrapperInteger(question, binding)
	}

	def private buildControlForTypeDecimal(Question question) {
		val binding = bindingBuilder.buildBindingForTypeDecimal(controls, question.expression)
		return new ControlWrapperDecimal(question, binding)
	}
	
	def private buildControlForTypeMoney(Question question) {
		val binding = bindingBuilder.buildBindingForTypeMoney(controls, question.expression)
		return new ControlWrapperMoney(question, binding)
	}

	def buildControlForTypeDate(Question question) {
		return new ControlWrapperDate(question, null)
	}

	def private EventHandler buildEventHandler() {
		return new EventHandler() {
			override handle(Event arg0) {
				bindingBuilder.invalidateBindings()
			}
		}
	}

	def getControlByName(String name) {
		return controls.filter[it.name == name].head()
	}

}

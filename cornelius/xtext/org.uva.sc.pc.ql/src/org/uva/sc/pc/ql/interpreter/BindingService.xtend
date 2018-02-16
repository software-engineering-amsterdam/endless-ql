package org.uva.sc.pc.ql.interpreter

import java.util.HashMap
import java.util.concurrent.Callable
import javafx.beans.binding.Bindings
import javafx.scene.control.CheckBox
import javafx.scene.control.Control
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import javax.inject.Inject
import org.uva.sc.pc.ql.qLang.Expression
import org.uva.sc.pc.ql.util.MissingCaseException

class BindingService {

	@Inject
	private var ExpressionEvaluator evaluator

	def buildBindingForTypeBoolean(HashMap<String, Control> controls, Expression expression) {
		val binding = Bindings.createBooleanBinding(new Callable<Boolean>() {

			override call() throws Exception {
				evaluator.<Boolean>evalExpression(expression, getExpressionArguments(controls, expression))
			}

		})
		return binding
	}

	def buildBindingForTypeString(HashMap<String, Control> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<String>evalExpression(expression, getExpressionArguments(controls, expression))
			}

		})
		return binding
	}

	def buildBindingForTypeInteger(HashMap<String, Control> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<Double>evalExpression(expression, getExpressionArguments(controls, expression)).intValue.
					toString
			}

		})
		return binding
	}

	def buildBindingForTypeDecimalAndMoney(HashMap<String, Control> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<Double>evalExpression(expression, getExpressionArguments(controls, expression)).toString
			}

		})
		return binding
	}

	def private getExpressionArguments(HashMap<String, Control> controls, Expression exp) {
		val result = new HashMap<String, Object>
		controls.forEach [ name, widget |
			switch widget {
				CheckBox: result.put(name, widget.selected)
				TextField: result.put(name, widget.text)
				DatePicker: result.put(name, widget.value)
				default: throw new MissingCaseException
			}
		]
		return result
	}

}

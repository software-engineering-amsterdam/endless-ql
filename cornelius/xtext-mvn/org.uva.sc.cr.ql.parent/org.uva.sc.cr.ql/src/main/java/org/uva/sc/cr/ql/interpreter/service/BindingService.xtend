package org.uva.sc.cr.ql.interpreter.service

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.concurrent.Callable
import javafx.beans.binding.Binding
import javafx.beans.binding.Bindings
import javax.inject.Inject
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapper
import org.uva.sc.cr.ql.qL.Expression

@Singleton
class BindingService {

	@Inject
	private var ExpressionEvaluator evaluator

	private List<Binding> bindings;

	new() {
		bindings = new ArrayList();
	}

	public def invalidateBindings() {
		bindings.forEach [
			it.invalidate
		]
	}

	def buildBindingForTypeBoolean(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createBooleanBinding(new Callable<Boolean>() {

			override call() throws Exception {
				evaluator.<Boolean>evaluateExpression(expression, getExpressionArguments(controls, expression))
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeString(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<String>evaluateExpression(expression, getExpressionArguments(controls, expression))
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeInteger(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<Double>evaluateExpression(expression, getExpressionArguments(controls, expression)).intValue.
					toString
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeDecimalAndMoney(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<Double>evaluateExpression(expression, getExpressionArguments(controls, expression)).toString
			}

		})
		bindings.add(binding)
		return binding
	}

	def private getExpressionArguments(List<ControlWrapper> controls, Expression exp) {
		val result = new HashMap<String, Object>
		controls.forEach [ control |
			result.put(control.name, control.value)
		]
		return result
	}

}

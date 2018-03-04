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
import org.uva.sc.cr.ql.interpreter.evaluator.ExpressionEvaluator
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.interpreter.evaluator.ExpressionEvaluatorMoney

@Singleton
class BindingService {

	@Inject
	private var ExpressionEvaluator evaluator
	
	@Inject
	private var ExpressionEvaluatorMoney evaluatorMoney

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
				evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), Boolean)
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeString(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), String)
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeInteger(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), Double).intValue.
					toString
			}

		})
		bindings.add(binding)
		return binding
	}

	def buildBindingForTypeDecimalAndMoney(List<ControlWrapper> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.evaluateExpression(expression, getExpressionArguments(controls, expression), Double).toString
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

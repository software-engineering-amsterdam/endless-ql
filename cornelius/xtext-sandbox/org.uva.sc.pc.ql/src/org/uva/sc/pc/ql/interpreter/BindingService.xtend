package org.uva.sc.pc.ql.interpreter

import java.util.HashMap
import java.util.concurrent.Callable
import javafx.beans.binding.Bindings
import javafx.scene.control.CheckBox
import javafx.scene.control.Control
import javafx.scene.control.TextField
import javax.inject.Inject
import org.uva.sc.pc.ql.qLang.Expression
import org.uva.sc.pc.ql.qLang.ExpressionQuestionRef
import org.uva.sc.pc.ql.qLang.util.MissingCaseException

class BindingService {

	@Inject
	private var ExpressionEvaluator evaluator

	def buildBindingForTypeBoolean(HashMap<Object, Control> controls, Expression expression) {
		val binding = Bindings.createBooleanBinding(new Callable<Boolean>() {

			override call() throws Exception {
				evaluator.<Boolean>evalExpression(expression, getExpressionArguments(controls, expression))
			}

		})
		return binding
	}

	def buildBindingForTypeString(HashMap<Object, Control> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<String>evalExpression(expression, getExpressionArguments(controls, expression))
			}

		})
		return binding
	}

	def buildBindingForTypeInteger(HashMap<Object, Control> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<Double>evalExpression(expression, getExpressionArguments(controls, expression)).intValue.
					toString
			}

		})
		return binding
	}

	def buildBindingForTypeDecimalAndMoney(HashMap<Object, Control> controls, Expression expression) {
		val binding = Bindings.createStringBinding(new Callable<String>() {

			override call() throws Exception {
				evaluator.<Double>evalExpression(expression, getExpressionArguments(controls, expression)).toString
			}

		})
		return binding
	}

	def private getExpressionArguments(HashMap<Object, Control> controls, Expression exp) {
		val result = new HashMap<String, Object>
		exp.eAllContents.filter[it instanceof ExpressionQuestionRef].forEach [
			var name = (it as ExpressionQuestionRef).question.name
			var widget = controls.get(name)
			switch widget {
				CheckBox: result.put(name, widget.selected)
				TextField: result.put(name, widget.text)
				default: throw new MissingCaseException
			}
		]
		return result
	}

}

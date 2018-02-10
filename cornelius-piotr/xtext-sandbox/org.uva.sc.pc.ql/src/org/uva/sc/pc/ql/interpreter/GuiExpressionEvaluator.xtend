package org.uva.sc.pc.ql.interpreter

import javafx.scene.control.CheckBox
import org.uva.sc.pc.ql.qLang.Expression
import org.uva.sc.pc.ql.qLang.ExpressionQuestionRef
import javafx.scene.control.TextField

class GuiExpressionEvaluator extends ExpressionEvaluator {
	
	def <T> evalExpression(Expression exp) {
		val stringExp = buildExpression(exp)
		
		exp.eAllContents.filter[it instanceof ExpressionQuestionRef].forEach [
			var name = (it as ExpressionQuestionRef).question.name
			var widget = JavaFxMain.CONTROLS.get(name)
			switch widget {
				CheckBox: engine.put(name, widget.selected)
				TextField: engine.put(name, widget.text)
			}
		]
		var result = engine.eval(stringExp)
		return result as T
	}
	
}
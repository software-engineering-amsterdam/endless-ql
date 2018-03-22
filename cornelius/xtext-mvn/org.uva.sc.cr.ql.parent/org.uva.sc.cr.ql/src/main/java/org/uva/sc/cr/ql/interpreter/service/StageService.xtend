package org.uva.sc.cr.ql.interpreter.service

import javafx.scene.layout.VBox
import javax.inject.Inject
import javax.inject.Singleton
import org.uva.sc.cr.ql.qL.BlockBody
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.Form
import org.uva.sc.cr.ql.util.ExpressionUtil

@Singleton
class StageService {

	@Inject
	private var ControlService controlService

	def buildGuiLayout(Form form) {
		buildPanelForBlock(form.body, null)
	}

	def private VBox buildPanelForBlock(BlockBody body, Expression expression) {
		val box = new VBox
		body.questions.forEach [
			val control = controlService.buildControlForQuestion(it, expression)
			box.children.add(control)
		]
		body.blocks.forEach [
			val ifBlockChild = buildPanelForBlock(it.ifBody, it.expression)
			box.children.add(ifBlockChild)
			if (it.elseBody !== null) {
				val elseBlockExpression = ExpressionUtil.buildElseBlockExpression(it.expression)
				val elseBlockChild = buildPanelForBlock(it.elseBody, elseBlockExpression)
				box.children.add(elseBlockChild)
			}
		]
		return box
	}

}

package org.uva.sc.cr.ql.interpreter.service

import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.layout.VBox
import javafx.stage.StageStyle
import javax.inject.Inject
import javax.inject.Singleton
import org.uva.sc.cr.ql.qL.BlockBody
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.Form
import org.uva.sc.cr.ql.util.ExpressionUtil

@Singleton
class DialogBuilder {

	@Inject
	var ControlBuilder controlBuilder

	def buildDialog(Form form) {
		val content = buildPanelForBlock(form.body, null)
		val dialog = new Alert(AlertType.CONFIRMATION);
		dialog.initStyle(StageStyle.UTILITY);
		dialog.title = form.name
		dialog.headerText = form.name
		dialog.dialogPane.content = content
		dialog.graphic = null
		return dialog
	}

	def private VBox buildPanelForBlock(BlockBody body, Expression expression) {
		val box = new VBox()
		body.questions.forEach [
			val control = controlBuilder.buildControlForQuestion(it, expression)
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

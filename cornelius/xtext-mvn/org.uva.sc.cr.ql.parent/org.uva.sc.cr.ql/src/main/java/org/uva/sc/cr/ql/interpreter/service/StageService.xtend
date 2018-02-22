package org.uva.sc.cr.ql.interpreter.service

import javafx.scene.layout.VBox
import javax.inject.Inject
import javax.inject.Singleton
import org.uva.sc.cr.ql.qL.Block
import org.uva.sc.cr.ql.qL.Form

@Singleton
class StageService {

	@Inject
	private var ControlService controlService

	@Inject
	private var BindingService bindingService

	def buildGuiLayout(Form form) {
		val root = new VBox
		form.body.questions.forEach [
			val control = controlService.buildControlForQuestion(it)
			root.children.add(control)
		]
		form.blocks.forEach [
			val block = buildPanelForBlock(it)
			root.children.add(block)
		]
		return root
	}

	def private buildPanelForBlock(Block block) {
		val box = new VBox();
		block.body.questions.forEach [
			val control = controlService.buildControlForQuestion(it)
			box.children.add(control)
		]
		val binding = bindingService.buildBindingForTypeBoolean(controlService.controls, block.expression)
		box.visibleProperty.bind(binding)
		return box
	}

}

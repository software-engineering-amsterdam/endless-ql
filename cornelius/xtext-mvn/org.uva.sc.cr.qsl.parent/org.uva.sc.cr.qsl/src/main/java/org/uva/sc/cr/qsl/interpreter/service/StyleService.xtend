package org.uva.sc.cr.qsl.interpreter.service

import java.util.List
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javax.inject.Inject
import javax.inject.Singleton
import org.controlsfx.dialog.Wizard
import org.controlsfx.dialog.Wizard.LinearFlow
import org.controlsfx.dialog.WizardPane
import org.uva.sc.cr.ql.interpreter.service.ControlService
import org.uva.sc.cr.qsl.qSL.DefaultStyle
import org.uva.sc.cr.qsl.qSL.Page
import org.uva.sc.cr.qsl.qSL.Section
import org.uva.sc.cr.qsl.qSL.Stylesheet

@Singleton
class StyleService {

	@Inject
	private ControlService controlService

	def styleLayout(Stylesheet stylesheet) {
		val wizard = new Wizard
		val wizardPanes = stylesheet.pages.map[buildPage(it)]
		wizard.setFlow(new LinearFlow(wizardPanes))
		return wizard
	}

	def private WizardPane buildPage(Page page) {
		val wizardPane = new WizardPane
		wizardPane.stylesheets.remove(0)
		val vbox = new VBox(10)
		val builtSections = page.sections.map[buildSection(it, page.defaultStyles)]
		vbox.children.addAll(builtSections)
		wizardPane.content = vbox
		return wizardPane
	}

	def private VBox buildSection(Section section, List<DefaultStyle> defaults) {
		val vbox = new VBox(10)
		vbox.children.add(buildSectionLabel(section))
		section.questions.forEach [ question |
			val controlWrapper = controlService.controls.filter[it.name == question.questionReference.name].head
			vbox.children.add(controlWrapper.controlWithLabel)
		]
		section.sections.forEach [
			val child = buildSection(it, section.defaultStyles)
			vbox.children.add(child)
		]
		// TODO handle defaults and widgets
		return vbox
	}

	def private Label buildSectionLabel(Section section) {
		val label = new Label(section.name)
		label.font = new Font(20)
		return label
	}

}

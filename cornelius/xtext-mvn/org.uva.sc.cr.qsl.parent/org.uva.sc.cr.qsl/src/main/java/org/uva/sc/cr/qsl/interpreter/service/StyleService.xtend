package org.uva.sc.cr.qsl.interpreter.service

import java.util.ArrayList
import java.util.List
import javafx.scene.Node
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
import org.uva.sc.cr.qsl.qSL.QuestionReference
import org.uva.sc.cr.qsl.qSL.Section
import org.uva.sc.cr.qsl.qSL.Stylesheet

@Singleton
class StyleService {

	@Inject
	private ControlService controlService
	
	@Inject
	private StyleControlService styleControlService

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

	def private VBox buildSection(Section section, List<DefaultStyle> defaultStyles) {
		
		val previousAndNewDefaultStyles = new ArrayList<DefaultStyle>
		previousAndNewDefaultStyles.addAll(defaultStyles)
		previousAndNewDefaultStyles.addAll(section.defaultStyles)
		
		val vbox = new VBox(10)
		vbox.children.add(buildSectionLabel(section))
		section.questions.forEach [
			val child = buildStyledControl(previousAndNewDefaultStyles, it)
			vbox.children.add(child)
		]
		section.sections.forEach [
			val child = buildSection(it, previousAndNewDefaultStyles)
			vbox.children.add(child)
		]
		return vbox
	}

	def private Node buildStyledControl(List<DefaultStyle> defaultStyles, QuestionReference questionReference) {
		val controlWrapper = controlService.getControlByName(questionReference.question.name)
		val defaultStyleToApply = getDefaultStyleForQuestionReference(defaultStyles, questionReference)
		var widget = questionReference.widget
		
		if(widget === null && defaultStyleToApply !== null){
			widget = defaultStyleToApply.widget
		}
		
		if(widget !== null){
			return styleControlService.style(controlWrapper, widget, defaultStyleToApply)
		} else {
			return styleControlService.styleDefaultControl(controlWrapper, defaultStyleToApply)
		}
	}
	
	def DefaultStyle getDefaultStyleForQuestionReference(List<DefaultStyle> defaultStyles, QuestionReference questionReference){
		for(defaultStyle: defaultStyles){
			if(defaultStyle.questionType == questionReference.question.type){
				return defaultStyle
			}
		}
		return null
	}

	def private Label buildSectionLabel(Section section) {
		val label = new Label(section.name)
		label.font = new Font(20)
		return label
	}

}

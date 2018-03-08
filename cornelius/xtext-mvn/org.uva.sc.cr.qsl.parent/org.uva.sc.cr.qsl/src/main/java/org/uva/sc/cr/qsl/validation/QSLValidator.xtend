package org.uva.sc.cr.qsl.validation

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.validation.Check
import org.uva.sc.cr.ql.qL.QLPackage
import org.uva.sc.cr.ql.qL.Question
import org.uva.sc.cr.qsl.qSL.Model
import org.uva.sc.cr.qsl.qSL.Page
import org.uva.sc.cr.qsl.qSL.QSLPackage
import org.uva.sc.cr.qsl.qSL.QuestionReference
import org.uva.sc.cr.qsl.qSL.Section
import org.uva.sc.cr.qsl.qSL.Stylesheet

class QSLValidator extends AbstractQSLValidator {

	public static val STYLESHEET_MISSING_QUESTION = 'stylesheetMissingQuestion'
	public static val STYLESHEET_MISSING_QUESTION_MESSAGE = 'All questions from the form have to placed by the stylesheet!'

	public static val STYLESHEET_QUESTION_MULTIPLE_REFERENCES = 'stylesheetQuestionMultipleReferences'
	public static val STYLESHEET_QUESTION_MULTIPLE_REFERENCES_MESSAGE = 'A question can only be placed once by the stylesheet!'

	public static val DEFAULT_STYLE_EXISTS_FOR_WIDGET = 'pageDefaultStyleExistsForWidget'
	public static val DEFAULT_STYLE_EXISTS_FOR_WIDGET_MESSAGE = 'A default style can only be defined once for a widget type inside a page or section!'

	@Check
	def checkAllFormQuestionsArePlaced(Question question) {

		val stylesheet = getStylesheet(question)

		val questionIsPlaced = stylesheet.eAllContents.filter[it instanceof QuestionReference].exists [
			val questionReference = it as QuestionReference
			questionReference.questionReference == question
		]
		if (!questionIsPlaced) {
			error(STYLESHEET_MISSING_QUESTION_MESSAGE, QLPackage.Literals.QUESTION__NAME, STYLESHEET_MISSING_QUESTION)
		}

	}

	@Check
	def checkQuestionIsOnlyPlacedOnce(QuestionReference questionReference) {

		val stylesheet = getStylesheet(questionReference)

		val otherReferences = stylesheet.eAllContents.
			filter[it instanceof QuestionReference && it != questionReference].filter [
				val otherQuestionReference = it as QuestionReference
				questionReference.questionReference == otherQuestionReference.questionReference
			]
		if (!otherReferences.empty) {
			error(STYLESHEET_QUESTION_MULTIPLE_REFERENCES_MESSAGE,
				QSLPackage.Literals.QUESTION_REFERENCE__QUESTION_REFERENCE, STYLESHEET_QUESTION_MULTIPLE_REFERENCES)
		}

	}

	@Check
	def checkPageHasOnlyOneDefaultStylePerWidget(Page page) {

		page.defaultStyles.forEach [
			val defaultStyle = it
			val questionType = defaultStyle.questionType
			val anotherDefinitionForTypeExists = page.defaultStyles.exists [
				it != defaultStyle && it.questionType == questionType
			]
			if (anotherDefinitionForTypeExists) {
				error(DEFAULT_STYLE_EXISTS_FOR_WIDGET_MESSAGE, QSLPackage.Literals.PAGE__DEFAULT_STYLES,
					DEFAULT_STYLE_EXISTS_FOR_WIDGET)
			}
		]

	}

	@Check
	def checkSectionHasOnlyOneDefaultStylePerWidget(Section section) {

		section.defaultStyles.forEach [
			val defaultStyle = it
			val questionType = defaultStyle.questionType
			val anotherDefinitionForTypeExists = section.defaultStyles.exists [
				it != defaultStyle && it.questionType == questionType
			]
			if (anotherDefinitionForTypeExists) {
				error(DEFAULT_STYLE_EXISTS_FOR_WIDGET_MESSAGE, QSLPackage.Literals.SECTION__DEFAULT_STYLES,
					DEFAULT_STYLE_EXISTS_FOR_WIDGET)
			}
		]

	}

	def dispatch getForm(Model model) {
		return model.form
	}

	def dispatch Stylesheet getStylesheet(EObject obj) {
		val parent = obj.eContainer
		return getStylesheet(parent)
	}

	def dispatch getStylesheet(Model model) {
		return model.stylesheet
	}

}

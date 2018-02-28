package org.uva.sc.cr.ql.validation

import java.util.ArrayList
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.validation.Check
import org.uva.sc.cr.ql.qL.BlockBody
import org.uva.sc.cr.ql.qL.ExpressionQuestionRef
import org.uva.sc.cr.ql.qL.Form
import org.uva.sc.cr.ql.qL.QLPackage
import org.uva.sc.cr.ql.qL.Question

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class QLValidator extends AbstractQLValidator {

	public static val BLOCK_MISSING_QUESTION = 'blockMissingQuestion'
	public static val BLOCK_MISSING_QUESTION_MESSAGE = 'At least one question is required!'

	public static val SELF_REFERNCE = "selfReference"
	public static val SELF_REFERNCE_MESSAGE = "The expression of a computed question cannot contain itself!"

	public static val FORWARD_REFERNCE = "forwardReference"
	public static val FORWARD_REFERNCE_MESSAGE = "The expression cannot contain a forward reference!"

	public static val LABEL_EXISTS = "labelExists"
	public static val LABEL_EXISTS_MESSAGE = "The label for this question already exists!"

	@Check
	def checkBlockHasQuestion(BlockBody blockBody) {

		if (blockBody.questions.isEmpty) {
			error(BLOCK_MISSING_QUESTION_MESSAGE, QLPackage.Literals.BLOCK_BODY__QUESTIONS, BLOCK_MISSING_QUESTION)
		}

	}

	@Check
	def checkQuestionSelfReference(Question question) {

		if (question.expression !== null) {
			question.expression.eContents.filter[it instanceof ExpressionQuestionRef].forEach [
				val questionRef = it as ExpressionQuestionRef
				if (questionRef.question.name == question.name)
					error(SELF_REFERNCE_MESSAGE, QLPackage.Literals.QUESTION__EXPRESSION, SELF_REFERNCE)
			]
		}

	}

	@Check
	def checkForForwardReferences(ExpressionQuestionRef questionRef) {
		val form = getForm(questionRef)

		val elementsInTreeBefore = new ArrayList<EObject>
		var found = false
		for (elem : form.eAllContents.toList) {

			if (!found) {
				elementsInTreeBefore.add(elem)
				if (elem == questionRef) {
					found = true
				}
			}

		}

		val questionsInTreeBefore = elementsInTreeBefore.filter[it instanceof Question]
		val questionExists = questionsInTreeBefore.exists [
			val question = it as Question
			question.name == questionRef.question.name
		]
		if (!questionExists) {
			error(FORWARD_REFERNCE_MESSAGE, QLPackage.Literals.EXPRESSION_QUESTION_REF__QUESTION, FORWARD_REFERNCE)
		}

	}

	@Check
	def checkForDuplicateLabels(Question question) {

		val form = getForm(question)

		val labelExists = form.eAllContents.filter[it instanceof Question && it != question].exists [
			val questionToCompare = it as Question
			question.label == questionToCompare.label
		]

		if (labelExists) {
			warning(LABEL_EXISTS_MESSAGE, QLPackage.Literals.QUESTION__LABEL, LABEL_EXISTS)
		}

	}

	def dispatch Form getForm(EObject obj) {
		val parent = obj.eContainer
		return getForm(parent)
	}

	def dispatch getForm(Form form) {
		return form
	}

}

package org.uva.sc.cr.qsl.validation

import org.eclipse.xtext.validation.Check
import org.uva.sc.cr.ql.qL.QuestionType
import org.uva.sc.cr.qsl.qSL.DefaultStyle
import org.uva.sc.cr.qsl.qSL.QSLPackage
import org.uva.sc.cr.qsl.qSL.QuestionReference
import org.uva.sc.cr.qsl.qSL.WidgetCheckbox
import org.uva.sc.cr.qsl.qSL.WidgetDropdown
import org.uva.sc.cr.qsl.qSL.WidgetRadio
import org.uva.sc.cr.qsl.qSL.WidgetSlider
import org.uva.sc.cr.qsl.qSL.WidgetSpinbox
import org.uva.sc.cr.qsl.qSL.WidgetText

class QSLWidgetValidator extends AbstractQSLValidator {

	public static val WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE = 'widgetNotAllowedForQuestionType'
	public static val WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE_MESSAGE = 'This type of widget is not allowed for the specified question type!'

	@Check
	def checkQuestionReferenceWidgetType(QuestionReference questionReference) {
		val widget = questionReference.widget
		if (widget !== null) {
			val allowed = isWidgetAllowedForQuestionType(widget, questionReference.question.type)
			if (!allowed) {
				error(WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE_MESSAGE, QSLPackage.Literals.QUESTION_REFERENCE__WIDGET,
					WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE)
			}
		}
	}

	@Check
	def checkDefaultStyleWidgetType(DefaultStyle defaultStyle) {
		val allowed = isWidgetAllowedForQuestionType(defaultStyle.widget, defaultStyle.questionType)
		if (!allowed) {
			error(WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE_MESSAGE, QSLPackage.Literals.DEFAULT_STYLE__WIDGET,
				WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE)
		}
	}

	dispatch def private isWidgetAllowedForQuestionType(WidgetCheckbox widget, QuestionType questionType) {
		switch questionType {
			case TYPE_BOOLEAN: return true
			case TYPE_DATE: return false
			case TYPE_DECIMAL: return false
			case TYPE_INTEGER: return false
			case TYPE_MONEY: return false
			case TYPE_STRING: return false
		}
	}

	dispatch def private isWidgetAllowedForQuestionType(WidgetSpinbox widget, QuestionType questionType) {
		switch questionType {
			case TYPE_BOOLEAN: return false
			case TYPE_DATE: return false
			case TYPE_DECIMAL: return true
			case TYPE_INTEGER: return true
			case TYPE_MONEY: return true
			case TYPE_STRING: return false
		}
	}

	dispatch def private isWidgetAllowedForQuestionType(WidgetSlider widget, QuestionType questionType) {
		switch questionType {
			case TYPE_BOOLEAN: return false
			case TYPE_DATE: return false
			case TYPE_DECIMAL: return true
			case TYPE_INTEGER: return true
			case TYPE_MONEY: return true
			case TYPE_STRING: return false
		}
	}

	dispatch def private isWidgetAllowedForQuestionType(WidgetText widget, QuestionType questionType) {
		switch questionType {
			case TYPE_BOOLEAN: return false
			case TYPE_DATE: return false
			case TYPE_DECIMAL: return true
			case TYPE_INTEGER: return true
			case TYPE_MONEY: return true
			case TYPE_STRING: return true
		}
	}

	dispatch def private isWidgetAllowedForQuestionType(WidgetRadio widget, QuestionType questionType) {
		switch questionType {
			case TYPE_BOOLEAN: return true
			case TYPE_DATE: return false
			case TYPE_DECIMAL: return false
			case TYPE_INTEGER: return false
			case TYPE_MONEY: return false
			case TYPE_STRING: return false
		}
	}

	dispatch def private isWidgetAllowedForQuestionType(WidgetDropdown widget, QuestionType questionType) {
		switch questionType {
			case TYPE_BOOLEAN: return true
			case TYPE_DATE: return false
			case TYPE_DECIMAL: return false
			case TYPE_INTEGER: return false
			case TYPE_MONEY: return false
			case TYPE_STRING: return false
		}
	}

}

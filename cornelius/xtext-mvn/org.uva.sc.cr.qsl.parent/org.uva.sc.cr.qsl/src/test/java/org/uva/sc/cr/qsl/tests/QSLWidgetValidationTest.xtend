package org.uva.sc.cr.qsl.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.ql.qL.QuestionType
import org.uva.sc.cr.qsl.qSL.Model
import org.uva.sc.cr.qsl.qSL.QSLPackage
import org.uva.sc.cr.qsl.validation.QSLWidgetValidator

@RunWith(XtextRunner)
@InjectWith(QSLInjectorProvider)
class QSLWidgetValidationTest {

	@Inject
	ParseHelper<Model> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void testTypeBoolean() {
		questionReferenceTestHelper("checkbox", QuestionType.TYPE_BOOLEAN, false)
		questionReferenceTestHelper("spinbox", QuestionType.TYPE_BOOLEAN, true)
		questionReferenceTestHelper("slider", QuestionType.TYPE_BOOLEAN, true)
		questionReferenceTestHelper("text", QuestionType.TYPE_BOOLEAN, true)
		questionReferenceTestHelper("radio", QuestionType.TYPE_BOOLEAN, false)
		questionReferenceTestHelper("dropdown", QuestionType.TYPE_BOOLEAN, false)

		defaultStyleTestHelper("checkbox", QuestionType.TYPE_BOOLEAN, false)
		defaultStyleTestHelper("spinbox", QuestionType.TYPE_BOOLEAN, true)
		defaultStyleTestHelper("slider", QuestionType.TYPE_BOOLEAN, true)
		defaultStyleTestHelper("text", QuestionType.TYPE_BOOLEAN, true)
		defaultStyleTestHelper("radio", QuestionType.TYPE_BOOLEAN, false)
		defaultStyleTestHelper("dropdown", QuestionType.TYPE_BOOLEAN, false)
	}

	@Test
	def void testTypeInteger() {
		questionReferenceTestHelper("checkbox", QuestionType.TYPE_INTEGER, true)
		questionReferenceTestHelper("spinbox", QuestionType.TYPE_INTEGER, false)
		questionReferenceTestHelper("slider", QuestionType.TYPE_INTEGER, false)
		questionReferenceTestHelper("text", QuestionType.TYPE_INTEGER, false)
		questionReferenceTestHelper("radio", QuestionType.TYPE_INTEGER, true)
		questionReferenceTestHelper("dropdown", QuestionType.TYPE_INTEGER, true)

		defaultStyleTestHelper("checkbox", QuestionType.TYPE_INTEGER, true)
		defaultStyleTestHelper("spinbox", QuestionType.TYPE_INTEGER, false)
		defaultStyleTestHelper("slider", QuestionType.TYPE_INTEGER, false)
		defaultStyleTestHelper("text", QuestionType.TYPE_INTEGER, false)
		defaultStyleTestHelper("radio", QuestionType.TYPE_INTEGER, true)
		defaultStyleTestHelper("dropdown", QuestionType.TYPE_INTEGER, true)
	}

	@Test
	def void testTypeDecimal() {
		questionReferenceTestHelper("checkbox", QuestionType.TYPE_DECIMAL, true)
		questionReferenceTestHelper("spinbox", QuestionType.TYPE_DECIMAL, false)
		questionReferenceTestHelper("slider", QuestionType.TYPE_DECIMAL, false)
		questionReferenceTestHelper("text", QuestionType.TYPE_DECIMAL, false)
		questionReferenceTestHelper("radio", QuestionType.TYPE_DECIMAL, true)
		questionReferenceTestHelper("dropdown", QuestionType.TYPE_DECIMAL, true)

		defaultStyleTestHelper("checkbox", QuestionType.TYPE_DECIMAL, true)
		defaultStyleTestHelper("spinbox", QuestionType.TYPE_DECIMAL, false)
		defaultStyleTestHelper("slider", QuestionType.TYPE_DECIMAL, false)
		defaultStyleTestHelper("text", QuestionType.TYPE_DECIMAL, false)
		defaultStyleTestHelper("radio", QuestionType.TYPE_DECIMAL, true)
		defaultStyleTestHelper("dropdown", QuestionType.TYPE_DECIMAL, true)
	}

	@Test
	def void testTypeMoney() {
		questionReferenceTestHelper("checkbox", QuestionType.TYPE_MONEY, true)
		questionReferenceTestHelper("spinbox", QuestionType.TYPE_MONEY, false)
		questionReferenceTestHelper("slider", QuestionType.TYPE_MONEY, false)
		questionReferenceTestHelper("text", QuestionType.TYPE_MONEY, false)
		questionReferenceTestHelper("radio", QuestionType.TYPE_MONEY, true)
		questionReferenceTestHelper("dropdown", QuestionType.TYPE_MONEY, true)

		defaultStyleTestHelper("checkbox", QuestionType.TYPE_MONEY, true)
		defaultStyleTestHelper("spinbox", QuestionType.TYPE_MONEY, false)
		defaultStyleTestHelper("slider", QuestionType.TYPE_MONEY, false)
		defaultStyleTestHelper("text", QuestionType.TYPE_MONEY, false)
		defaultStyleTestHelper("radio", QuestionType.TYPE_MONEY, true)
		defaultStyleTestHelper("dropdown", QuestionType.TYPE_MONEY, true)
	}

	@Test
	def void testTypeString() {
		questionReferenceTestHelper("checkbox", QuestionType.TYPE_STRING, true)
		questionReferenceTestHelper("spinbox", QuestionType.TYPE_STRING, true)
		questionReferenceTestHelper("slider", QuestionType.TYPE_STRING, true)
		questionReferenceTestHelper("text", QuestionType.TYPE_STRING, false)
		questionReferenceTestHelper("radio", QuestionType.TYPE_STRING, true)
		questionReferenceTestHelper("dropdown", QuestionType.TYPE_STRING, true)

		defaultStyleTestHelper("checkbox", QuestionType.TYPE_STRING, true)
		defaultStyleTestHelper("spinbox", QuestionType.TYPE_STRING, true)
		defaultStyleTestHelper("slider", QuestionType.TYPE_STRING, true)
		defaultStyleTestHelper("text", QuestionType.TYPE_STRING, false)
		defaultStyleTestHelper("radio", QuestionType.TYPE_STRING, true)
		defaultStyleTestHelper("dropdown", QuestionType.TYPE_STRING, true)
	}

	@Test
	def void testTypeDate() {
		questionReferenceTestHelper("checkbox", QuestionType.TYPE_DATE, true)
		questionReferenceTestHelper("spinbox", QuestionType.TYPE_DATE, true)
		questionReferenceTestHelper("slider", QuestionType.TYPE_DATE, true)
		questionReferenceTestHelper("text", QuestionType.TYPE_DATE, true)
		questionReferenceTestHelper("radio", QuestionType.TYPE_DATE, true)
		questionReferenceTestHelper("dropdown", QuestionType.TYPE_DATE, true)

		defaultStyleTestHelper("checkbox", QuestionType.TYPE_DATE, true)
		defaultStyleTestHelper("spinbox", QuestionType.TYPE_DATE, true)
		defaultStyleTestHelper("slider", QuestionType.TYPE_DATE, true)
		defaultStyleTestHelper("text", QuestionType.TYPE_DATE, true)
		defaultStyleTestHelper("radio", QuestionType.TYPE_DATE, true)
		defaultStyleTestHelper("dropdown", QuestionType.TYPE_DATE, true)
	}

	def private questionReferenceTestHelper(String widget, QuestionType questionType, boolean expectError) {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "BlaBla" q1: «questionType.literal»
			}
			
			stylesheet taxOfficeExample 
			  page Housing {
			    section "Buying"
			      question q1  
			        widget «widget» «IF widget == "radio" || widget == "dropdown"»("YES", "NO")«ENDIF»
			  }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		if (expectError) {
			validationTestHelper.assertError(result, QSLPackage.eINSTANCE.questionReference,
				QSLWidgetValidator.WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE)
		} else {
			validationTestHelper.assertNoErrors(result)
		}
	}

	def private defaultStyleTestHelper(String widget, QuestionType questionType, boolean expectError) {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "BlaBla" q1: boolean
			}
			
			stylesheet taxOfficeExample 
			  page Housing {
			    section "Buying"
			      question q1  
			    default «questionType.literal» widget «widget» «IF widget == "radio" || widget == "dropdown"»("YES", "NO")«ENDIF»
			  }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		if (expectError) {
			validationTestHelper.assertError(result, QSLPackage.eINSTANCE.defaultStyle,
				QSLWidgetValidator.WIDGET_NOT_ALLOWED_FOR_QUESTION_TYPE)
		} else {
			validationTestHelper.assertNoErrors(result)
		}
	}
}

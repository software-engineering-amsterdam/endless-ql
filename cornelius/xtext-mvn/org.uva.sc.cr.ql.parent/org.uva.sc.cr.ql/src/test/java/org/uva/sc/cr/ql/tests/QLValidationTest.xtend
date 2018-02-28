package org.uva.sc.cr.ql.tests

import com.google.inject.Inject
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.ql.qL.Form
import org.uva.sc.cr.ql.qL.QLPackage
import org.uva.sc.cr.ql.validation.QLValidator

@RunWith(XtextRunner)
@InjectWith(QLInjectorProvider)
class QLValidationTest {

	@Inject
	ParseHelper<Form> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void testErrorOnDuplicateVariable() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a house?" q1: string
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLPackage.eINSTANCE.question, "")
	}

	@Test
	def void testErrorOnForwardReference() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				if(q2){
					"Do you have a house?" q2: boolean
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionQuestionRef,
			QLValidator.FORWARD_REFERNCE)
	}

	@Test
	def void testErrorOnSelfReference() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean = (q1 && q1)
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLPackage.eINSTANCE.question, QLValidator.SELF_REFERNCE)
	}

	@Test
	def void testErrorOnEmptyForm() {
		val result = parseHelper.parse('''
			form TestForm{
				
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLPackage.eINSTANCE.blockBody, QLValidator.BLOCK_MISSING_QUESTION)
	}

	@Test
	def void testErrorOnEmptyBlock() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a pet?" q2: boolean
				if(q1 && q2){
					
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLPackage.eINSTANCE.blockBody, QLValidator.BLOCK_MISSING_QUESTION)
	}

	@Test
	def void testErrorOnUndefinedQuestion() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a pet?" q2: boolean
				if(q3){
					"Do you have a pet?" q4: boolean
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLPackage.eINSTANCE.expressionQuestionRef,
			Diagnostic.LINKING_DIAGNOSTIC)
	}

	@Test
	def void testWarningOnDuplicateLabel() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a pet?" q2: boolean
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertWarning(result, QLPackage.eINSTANCE.question, QLValidator.LABEL_EXISTS)
	}

}

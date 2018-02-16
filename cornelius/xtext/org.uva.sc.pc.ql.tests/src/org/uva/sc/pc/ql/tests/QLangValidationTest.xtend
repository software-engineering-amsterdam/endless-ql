package org.uva.sc.pc.ql.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.pc.ql.qLang.Form
import org.uva.sc.pc.ql.qLang.QLangPackage
import org.uva.sc.pc.ql.validation.QLangValidator

@RunWith(XtextRunner)
@InjectWith(QLangInjectorProvider)
class QLangValidationTest {

	@Inject
	ParseHelper<Form> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void testErrorOnDuplicateVariable() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a house?" q1: boolean
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLangPackage.eINSTANCE.question, "")
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

		validationTestHelper.assertError(result, QLangPackage.eINSTANCE.expressionQuestionRef,
			QLangValidator.FORWARD_REFERNCE)
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

		validationTestHelper.assertError(result, QLangPackage.eINSTANCE.question, QLangValidator.SELF_REFERNCE)
	}

	@Test
	def void testErrorOnEmptyForm() {
		val result = parseHelper.parse('''
			form TestForm{
				
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLangPackage.eINSTANCE.blockBody,
			QLangValidator.BLOCK_MISSING_QUESTION)
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

		validationTestHelper.assertError(result, QLangPackage.eINSTANCE.blockBody,
			QLangValidator.BLOCK_MISSING_QUESTION)
	}

}

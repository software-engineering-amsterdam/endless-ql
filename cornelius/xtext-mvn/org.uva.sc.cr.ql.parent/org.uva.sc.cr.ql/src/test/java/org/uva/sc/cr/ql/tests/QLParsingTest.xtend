package org.uva.sc.cr.ql.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.ql.qL.Form

@RunWith(XtextRunner)
@InjectWith(QLInjectorProvider)
class QLParsingTest {

	@Inject
	ParseHelper<Form> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void loadModel() {
		val result = parseHelper.parse('''
			form TestForm{
				"Do you have a pet?" q1: boolean
				"Do you have a house?" q2: boolean
				if(q1){
					"Do you have a dog?" q1_1: boolean = (q1 && q2)
					"Do you have a cat?" q1_2: boolean = (q1 || q2)
				} else {
					"Do you have a car?" q3: boolean
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertNoErrors(result)
	}

	@Test
	def void loadModelWithAllQuestionTypes() {
		val result = parseHelper.parse('''
			form TestForm{
				"Q?" q1: boolean
				"Q?" q2: string
				"Q?" q3: integer
				"Q?" q4: decimal
				"Q?" q5: money
				"Q?" q6: date
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertNoErrors(result)
	}

	@Test
	def void loadModelWithAllPossibleEpression() {
		val result = parseHelper.parse('''
			form TestForm{
				"Q?" qb_1: boolean
				"Q?" qb_2: boolean
				"Q?" qs_1: string
				"Q?" qs_2: string
				"Q?" qi_1: integer
				"Q?" qi_2: integer
				"Q?" qd_1: decimal
				"Q?" qd_2: decimal
				"Q?" qm_1: money
				"Q?" qm_2: money
				"Q?" qda_1: date
				"Q?" qda_2: date
				if(qb_1 && qb_2){
					"Q?" qs_3: string = (qs_1 + qs_2)
					"Q?" qs_4: boolean = (qs_1 == qs_2)
					"Q?" qs_5: boolean = (qs_1 != qs_2)
				}
				if(qb_1 || qb_2){
					"Q?" qi_3: integer = (qi_1 + qi_2)
					"Q?" qi_4: integer = (qi_1 - qi_2)
					"Q?" qi_5: integer = (qi_1 * qi_2)
					"Q?" qi_6: integer = (qi_1 / qi_2)
					"Q?" qi_7: boolean = (qi_1 < qi_2)
					"Q?" qi_8: boolean = (qi_1 <= qi_2)
					"Q?" qi_9: boolean = (qi_1 > qi_2)
					"Q?" qi_10: boolean = (qi_1 >= qi_2)
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertNoErrors(result)
	}

}

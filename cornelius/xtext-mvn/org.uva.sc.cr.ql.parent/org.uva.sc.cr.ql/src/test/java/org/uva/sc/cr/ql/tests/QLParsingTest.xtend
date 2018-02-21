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
				}
			}
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertNoErrors(result)
	}

}

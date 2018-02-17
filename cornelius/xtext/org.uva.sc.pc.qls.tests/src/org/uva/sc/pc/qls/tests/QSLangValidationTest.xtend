package org.uva.sc.pc.qls.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.pc.qls.qSLang.Model
import org.uva.sc.pc.qls.qSLang.QSLangPackage

@RunWith(XtextRunner)
@InjectWith(QSLangInjectorProvider)
class QSLangValidationTest {
	@Inject
	ParseHelper<Model> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void testQuestionIsOnlyPlacedOnce() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			  "Did you buy a house in 2010?"
			    hasBoughtHouse: boolean
			  "Did you enter a loan?"
			    hasMaintLoan: boolean
			    
			  if (hasSoldHouse) {
			    "What was the selling price?"
			      sellingPrice: money
			    "Private debts for the sold house:"
			      privateDebt: money
			    "Value residue:"
			      valueResidue: money = 
			        (sellingPrice - privateDebt)
			  }
			  
			}
			
			stylesheet taxOfficeExample 
			  page Housing {
			    section "Buying"
			      question hasBoughtHouse  
			        widget checkbox 
			    section "Loaning"  
			      question hasMaintLoan
			    section "Loaning2"  
			      question hasMaintLoan
			  }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLangPackage.eINSTANCE.questionRef, "")
	}

}

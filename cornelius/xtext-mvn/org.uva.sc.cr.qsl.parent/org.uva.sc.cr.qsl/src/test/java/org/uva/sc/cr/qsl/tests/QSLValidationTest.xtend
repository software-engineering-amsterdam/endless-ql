package org.uva.sc.cr.qsl.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.ql.qL.QLPackage
import org.uva.sc.cr.qsl.qSL.Model
import org.uva.sc.cr.qsl.qSL.QSLPackage
import org.uva.sc.cr.qsl.validation.QSLValidator

@RunWith(XtextRunner)
@InjectWith(QSLInjectorProvider)
class QSLValidationTest {
	@Inject
	ParseHelper<Model> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def void testErrorOnQuestionIsPlacedMoreThanOnce() {
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

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.questionReference,
			QSLValidator.STYLESHEET_QUESTION_MULTIPLE_REFERENCES)
	}

	@Test
	def void testErrorOnSectionHasMoreThanOneDefaultStylePerWidget() {
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
			    section "Buying" {
			      question hasBoughtHouse  
			        widget checkbox
			        default boolean {
			        	widget checkbox
			        }
			        default boolean {
			        	widget checkbox
			        }
			      }
			    
			  }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.section,
			QSLValidator.DEFAULT_STYLE_EXISTS_FOR_WIDGET)
	}

	@Test
	def void testErrorOnPageHasMoreThanOneDefaultStylePerWidget() {
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
			    section "Buying" {
			      question hasBoughtHouse  
			        widget checkbox
			      }
			    default boolean {
			  widget checkbox
				}
				default boolean {
					widget checkbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.page,
			QSLValidator.DEFAULT_STYLE_EXISTS_FOR_WIDGET)
	}

	@Test
	def void testErrorOnDefinedQuestionMissingInStylesheet() {
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
			    
			  }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QLPackage.eINSTANCE.question, QSLValidator.STYLESHEET_MISSING_QUESTION)
	}

}

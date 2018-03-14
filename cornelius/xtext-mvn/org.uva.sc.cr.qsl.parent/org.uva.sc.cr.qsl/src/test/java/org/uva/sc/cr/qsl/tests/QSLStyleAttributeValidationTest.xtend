package org.uva.sc.cr.qsl.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.uva.sc.cr.qsl.qSL.Model
import org.uva.sc.cr.qsl.qSL.QSLPackage
import org.uva.sc.cr.qsl.validation.QSLStyleAttributeValidator

@RunWith(XtextRunner)
@InjectWith(QSLInjectorProvider)
class QSLStyleAttributeValidationTest {

	@Inject
	ParseHelper<Model> parseHelper

	@Inject
	ValidationTestHelper validationTestHelper;

	@Test
	def testStyleAttributeWidthErrorOnValueSmallerThan1() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			}
			stylesheet taxOfficeExample 
			  page Housing {
			    default money {
			  width: 0
			  widget spinbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.styleAttribute,
			QSLStyleAttributeValidator.STYLE_ATTRIBUTE_WIDTH_LENGTH)
	}

	@Test
	def testStyleAttributeWidthErrorOnValueLargerThan600() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			}
			stylesheet taxOfficeExample 
			  page Housing {
			    default money {
			  width: 601
			  widget spinbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.styleAttribute,
			QSLStyleAttributeValidator.STYLE_ATTRIBUTE_WIDTH_LENGTH)
	}

	@Test
	def testStyleAttributeFontUnavailable() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			}
			stylesheet taxOfficeExample 
			  page Housing {
			    default money {
			  font: "Arial"
			  widget spinbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.styleAttribute,
			QSLStyleAttributeValidator.STYLE_ATTRIBUTE_FONT_UNKNOWN)
	}
	
	@Test
	def testStyleAttributeFontSizeErrorOnValueSmallerThan1() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			}
			stylesheet taxOfficeExample 
			  page Housing {
			    default money {
			  fontsize: 0
			  widget spinbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.styleAttribute,
			QSLStyleAttributeValidator.STYLE_ATTRIBUTE_FONT_SIZE)
	}
	
	@Test
	def testStyleAttributeFontSizeErrorOnValueLargerThan50() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			}
			stylesheet taxOfficeExample 
			  page Housing {
			    default money {
			  fontsize: 51
			  widget spinbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.styleAttribute,
			QSLStyleAttributeValidator.STYLE_ATTRIBUTE_FONT_SIZE)
	}
	
	@Test
	def testStyleAttributeColorLessThan7Chars() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			}
			stylesheet taxOfficeExample 
			  page Housing {
			    default money {
			  color: #12345
			  widget spinbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.styleAttribute,
			QSLStyleAttributeValidator.STYLE_ATTRIBUTE_COLOR_LENGTH)
	}
	
	@Test
	def testStyleAttributeColorMoreThan7Chars() {
		val result = parseHelper.parse('''
			form taxOfficeExample { 
			  "Did you sell a house in 2010?" 
			    hasSoldHouse: boolean
			}
			stylesheet taxOfficeExample 
			  page Housing {
			    default money {
			  color: #1234567
			  widget spinbox
				}
				 }
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)

		validationTestHelper.assertError(result, QSLPackage.eINSTANCE.styleAttribute,
			QSLStyleAttributeValidator.STYLE_ATTRIBUTE_COLOR_LENGTH)
	}

}

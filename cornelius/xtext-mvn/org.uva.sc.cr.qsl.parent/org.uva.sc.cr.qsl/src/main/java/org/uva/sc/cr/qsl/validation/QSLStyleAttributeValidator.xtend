package org.uva.sc.cr.qsl.validation

import java.awt.GraphicsEnvironment
import org.eclipse.xtext.validation.Check
import org.uva.sc.cr.qsl.qSL.QSLPackage
import org.uva.sc.cr.qsl.qSL.StyleAttributeColor
import org.uva.sc.cr.qsl.qSL.StyleAttributeFont
import org.uva.sc.cr.qsl.qSL.StyleAttributeFontSize
import org.uva.sc.cr.qsl.qSL.StyleAttributeWidth

class QSLStyleAttributeValidator extends AbstractQSLValidator {

	public static val STYLE_ATTRIBUTE_WIDTH_MIN_LENGTH = 1
	public static val STYLE_ATTRIBUTE_WIDTH_MAX_LENGTH = 600
	public static val STYLE_ATTRIBUTE_WIDTH_LENGTH = 'styleAttributeWidthLength'
	public static val STYLE_ATTRIBUTE_WIDTH_LENGTH_MESSAGE = '''The width of a styled attribute must be between «STYLE_ATTRIBUTE_WIDTH_MIN_LENGTH» and «STYLE_ATTRIBUTE_WIDTH_MAX_LENGTH»!'''

	public static val STYLE_ATTRIBUTE_FONT_UNKNOWN = 'styleAttributeFontUnknown'
	public static val STYLE_ATTRIBUTE_FONT_UNKNOWN_MESSAGE = 'The specified font is not available on your system!'

	public static val STYLE_ATTRIBUTE_FONT_MIN_SIZE = 1
	public static val STYLE_ATTRIBUTE_FONT_MAX_SIZE = 50
	public static val STYLE_ATTRIBUTE_FONT_SIZE = 'styleAttributeFontSize'
	public static val STYLE_ATTRIBUTE_FONT_SIZE_MESSAGE = '''The font size must be between «STYLE_ATTRIBUTE_FONT_MIN_SIZE» and «STYLE_ATTRIBUTE_FONT_MAX_SIZE»!'''

	public static val STYLE_ATTRIBUTE_COLOR_EXACT_LENGTH = 7
	public static val STYLE_ATTRIBUTE_COLOR_LENGTH = 'styleAttributeColor'
	public static val STYLE_ATTRIBUTE_COLOR_LENGTH_MESSAGE = 'The color must be specified with a # sign followed by 6 hex characters!'

	@Check
	def checkStyleAttributeWidth(StyleAttributeWidth styleAttributeWidth) {
		if (styleAttributeWidth.value < STYLE_ATTRIBUTE_WIDTH_MIN_LENGTH) {
			error(STYLE_ATTRIBUTE_WIDTH_LENGTH_MESSAGE, QSLPackage.Literals.STYLE_ATTRIBUTE_WIDTH__VALUE,
				STYLE_ATTRIBUTE_WIDTH_LENGTH)
		} else if (styleAttributeWidth.value > STYLE_ATTRIBUTE_WIDTH_MAX_LENGTH) {
			error(STYLE_ATTRIBUTE_WIDTH_LENGTH_MESSAGE, QSLPackage.Literals.STYLE_ATTRIBUTE_WIDTH__VALUE,
				STYLE_ATTRIBUTE_WIDTH_LENGTH)
		}
	}

	@Check
	def checkStyleAttributeFont(StyleAttributeFont styleAttributeFont) {
		val font = styleAttributeFont.value
		val systemFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		if (!systemFonts.contains(font)) {
			error(STYLE_ATTRIBUTE_FONT_UNKNOWN_MESSAGE, QSLPackage.Literals.STYLE_ATTRIBUTE_FONT__VALUE,
				STYLE_ATTRIBUTE_FONT_UNKNOWN)
		}
	}

	@Check
	def checkStyleAttributeFontSize(StyleAttributeFontSize styleAttributeFontSize) {
		if (styleAttributeFontSize.value <= STYLE_ATTRIBUTE_FONT_MIN_SIZE) {
			error(STYLE_ATTRIBUTE_FONT_SIZE_MESSAGE, QSLPackage.Literals.STYLE_ATTRIBUTE_FONT_SIZE__VALUE,
				STYLE_ATTRIBUTE_FONT_SIZE)
		} else if (styleAttributeFontSize.value > STYLE_ATTRIBUTE_FONT_MAX_SIZE) {
			error(STYLE_ATTRIBUTE_FONT_SIZE_MESSAGE, QSLPackage.Literals.STYLE_ATTRIBUTE_FONT_SIZE__VALUE,
				STYLE_ATTRIBUTE_FONT_SIZE)
		}
	}

	@Check
	def checkStyleAttributeColor(StyleAttributeColor styleAttributeColor) {
		if (styleAttributeColor.value.length != STYLE_ATTRIBUTE_COLOR_EXACT_LENGTH) {
			error(STYLE_ATTRIBUTE_COLOR_LENGTH_MESSAGE, QSLPackage.Literals.STYLE_ATTRIBUTE_COLOR__VALUE,
				STYLE_ATTRIBUTE_COLOR_LENGTH)
		}
	}

}

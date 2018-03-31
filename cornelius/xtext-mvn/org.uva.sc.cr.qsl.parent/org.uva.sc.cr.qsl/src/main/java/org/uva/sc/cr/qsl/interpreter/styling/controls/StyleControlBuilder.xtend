package org.uva.sc.cr.qsl.interpreter.styling.controls

import java.util.List
import javafx.event.Event
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.layout.HBox
import javax.inject.Inject
import javax.inject.Singleton
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapper
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperBoolean
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperDecimal
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperInteger
import org.uva.sc.cr.ql.interpreter.controls.ControlWrapperMoney
import org.uva.sc.cr.qsl.qSL.DefaultStyle
import org.uva.sc.cr.qsl.qSL.StyleAttribute
import org.uva.sc.cr.qsl.qSL.StyleAttributeColor
import org.uva.sc.cr.qsl.qSL.StyleAttributeFont
import org.uva.sc.cr.qsl.qSL.StyleAttributeFontSize
import org.uva.sc.cr.qsl.qSL.StyleAttributeWidth
import org.uva.sc.cr.qsl.qSL.Widget

@Singleton
class StyleControlBuilder {

	@Inject
	private StyleControlBooleanBuilder styleControlBooleanBuilder

	@Inject
	private StyleControlIntegerBuilder styleControlIntegerBuilder

	@Inject
	private StyleControlDecimalBuilder styleControlDecimalBuilder

	@Inject
	private StyleControlMoneyBuilder styleControlMoneyBuilder

	def HBox styleDefaultControl(ControlWrapper controlWrapper, DefaultStyle defaultStyleToApply) {
		val hbox = controlWrapper.controlWithLabel
		applyDefaultStyle(hbox, defaultStyleToApply)
		return hbox
	}

	def dispatch HBox style(ControlWrapper controlWrapper, Widget widget, DefaultStyle defaultStyleToApply) {
		return styleDefaultControl(controlWrapper, defaultStyleToApply)
	}

	def dispatch HBox style(ControlWrapperBoolean controlWrapper, Widget widget, DefaultStyle defaultStyleToApply) {
		return styleControlBooleanBuilder.styleBoolean(controlWrapper, widget, defaultStyleToApply)
	}

	def dispatch HBox style(ControlWrapperInteger controlWrapper, Widget widget, DefaultStyle defaultStyleToApply) {
		return styleControlIntegerBuilder.styleInteger(controlWrapper, widget, defaultStyleToApply)
	}

	def dispatch HBox style(ControlWrapperDecimal controlWrapper, Widget widget, DefaultStyle defaultStyleToApply) {
		return styleControlDecimalBuilder.styleDecimal(controlWrapper, widget, defaultStyleToApply)
	}

	def dispatch HBox style(ControlWrapperMoney controlWrapper, Widget widget, DefaultStyle defaultStyleToApply) {
		return styleControlMoneyBuilder.styleMoney(controlWrapper, widget, defaultStyleToApply)
	}

	protected def copyControlConfiguration(Control source, Control destination) {
		destination.addEventHandler(EventType.ROOT, new EventHandler() {

			override handle(Event arg0) {
				source.fireEvent(arg0)
			}

		})
		destination.visibleProperty().bindBidirectional(source.visibleProperty())
		destination.disableProperty().bindBidirectional(source.disableProperty())
	}

	protected def buildHBoxAndApplyDefaultStyle(DefaultStyle defaultStyleToApply, Node... controls) {
		val hbox = new HBox()
		hbox.children.addAll(controls)
		applyDefaultStyle(hbox, defaultStyleToApply)
		return hbox
	}

	protected def applyDefaultStyle(HBox hbox, DefaultStyle defaultStyleToApply) {
		if (defaultStyleToApply !== null) {
			hbox.children.forEach [
				applyStyleAttributes(it as Control, defaultStyleToApply.styles)
			]
		}
	}

	protected def applyStyleAttributes(Control control, List<StyleAttribute> styleAttributes) {
		styleAttributes.forEach [
			applyStyleAttribute(control, it)
		]
	}

	protected dispatch def applyStyleAttribute(Control control, StyleAttributeFont styleAttribute) {
		control.style = control.style + "-fx-font-family :" + styleAttribute.value + ";"
	}

	protected dispatch def applyStyleAttribute(Control control, StyleAttributeFontSize styleAttribute) {
		control.style = control.style + "-fx-font-size :" + styleAttribute.value + ";"
	}

	protected dispatch def applyStyleAttribute(Control control, StyleAttributeWidth styleAttribute) {
		control.style = control.style + "width :" + styleAttribute.value + ";"
	}

	protected dispatch def applyStyleAttribute(Control control, StyleAttributeColor styleAttribute) {
		control.style = control.style + "-fx-background-color :" + styleAttribute.value + ";"
	}

}

package org.uva.sc.cr.qsl.interpreter

import javafx.stage.Stage
import javax.inject.Inject
import org.uva.sc.cr.ql.interpreter.QLJavaFxApplication
import org.uva.sc.cr.qsl.QSLStandaloneSetup
import org.uva.sc.cr.qsl.interpreter.styling.StyleBuilder
import org.uva.sc.cr.qsl.qSL.Model

class QSLJavaFxApplication extends QLJavaFxApplication {

	@Inject
	var StyleBuilder styleBuilder

	override createInjector() {
		return new QSLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}

	override start(Stage primaryStage) {
		val model = parseFileAndValidate().allContents.head() as Model
		val form = model.form
		val stylesheet = model.stylesheet
		if (stylesheet !== null) {
			dialogBuilder.buildDialog(form)
			val wizard = styleBuilder.buildStyledDialog(form, stylesheet)
			wizard.showAndWait()
		} else {
			super.start(primaryStage)
		}
	}

}

package org.uva.sc.cr.qsl.interpreter

import javafx.stage.Stage
import javax.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
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

	override getForm(Resource astData) {
		val model = astData.allContents.head() as Model
		return model.form
	}

	def private getStylesheet(Resource astData) {
		val model = astData.allContents.head() as Model
		return model.stylesheet
	}

	override start(Stage primaryStage) {
		val astData = parseFileAndValidate()
		val form = getForm(astData)
		val stylesheet = getStylesheet(astData)
		if (stylesheet !== null) {
			dialogBuilder.buildDialog(form)
			val wizard = styleBuilder.buildStyledDialog(form, stylesheet)
			wizard.showAndWait()
		} else {
			super.start(primaryStage)
		}
	}

}

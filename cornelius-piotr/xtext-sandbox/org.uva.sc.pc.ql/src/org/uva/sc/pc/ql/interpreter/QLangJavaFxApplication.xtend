package org.uva.sc.pc.ql.interpreter

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import javax.inject.Inject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.uva.sc.pc.ql.QLangStandaloneSetup
import org.uva.sc.pc.ql.qLang.Form

class QLangJavaFxApplication extends Application {

	private var Resource astData

	@Inject
	private var StageService stageService

	override init() {
		val file = parameters.raw.get(0)
		val injector = new QLangStandaloneSetup().createInjectorAndDoEMFRegistration()
		val rs = injector.getInstance(ResourceSet)
		astData = rs.getResource(URI.createFileURI(file), true)

		val validator = injector.getInstance(IResourceValidator)
		var error = false
		val issues = validator.validate(astData, CheckMode.ALL, CancelIndicator.NullImpl)
		for (i : issues) {
			println(i)
			if(i.severity == Severity.ERROR) error = true
		}

		if (error)
			System.exit(0)

		injector.injectMembers(this)
	}

	override start(Stage primaryStage) {

		val form = astData.allContents.head as Form
		val rootStage = stageService.buildGuiLayout(form)

		var scene = new Scene(rootStage, 800, 600)
		primaryStage.setTitle(form.name)
		primaryStage.setScene(scene)
		primaryStage.show()
	}

}

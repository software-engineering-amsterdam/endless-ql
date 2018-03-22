package org.uva.sc.cr.ql.interpreter

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
import org.uva.sc.cr.ql.QLStandaloneSetup
import org.uva.sc.cr.ql.qL.Form
import org.uva.sc.cr.ql.interpreter.service.StageBuilder

class QLJavaFxApplication extends Application {

	protected var Resource astData

	@Inject
	protected var StageBuilder stageBuilder

	override init() {
		val file = parameters.raw.get(0)
		parseFile(file)
		validateAST()
	}

	def private void parseFile(String file) {
		val injector = createInjector()
		val resourceSet = injector.getInstance(ResourceSet)
		astData = resourceSet.getResource(URI.createFileURI(file), true)
		injector.injectMembers(this)
	}

	def createInjector() {
		return new QLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}

	def private void validateAST() {
		val validator = createInjector().getInstance(IResourceValidator)

		val issues = validator.validate(astData, CheckMode.ALL, CancelIndicator.NullImpl)
		val errors = issues.filter[it.severity == Severity.ERROR]

		errors.forEach [
			println(it)
		]

		if (!errors.empty)
			System.exit(0)

		val warnings = issues.filter[it.severity == Severity.WARNING]
		warnings.forEach [
			println(it)
		]
	}

	override start(Stage primaryStage) {
		val form = getForm()
		val rootStage = stageBuilder.buildGuiLayout(form)

		val scene = new Scene(rootStage, 800, 600)
		primaryStage.setTitle(form.name)
		primaryStage.setScene(scene)
		primaryStage.show()
	}

	def getForm() {
		return astData.allContents.head() as Form
	}

}

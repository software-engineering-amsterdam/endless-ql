package org.uva.sc.cr.ql.interpreter

import java.io.File
import java.util.List
import javafx.application.Application
import javafx.stage.FileChooser
import javafx.stage.Stage
import javax.inject.Inject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.eclipse.xtext.validation.Issue
import org.uva.sc.cr.ql.QLStandaloneSetup
import org.uva.sc.cr.ql.interpreter.service.DialogBuilder
import org.uva.sc.cr.ql.qL.Form

class QLJavaFxApplication extends Application {

	@Inject
	protected var DialogBuilder dialogBuilder

	def private openFile() {
		val fileChooser = new FileChooser()
		fileChooser.setTitle("Open File")
		val file = fileChooser.showOpenDialog(null)
		if (file === null) {
			DialogUtil.showErrorAndExit("No file selected!", "Please select a file!", false)
		}
		return file
	}

	def private parseFile(File file) {
		val injector = createInjector()
		val resourceSet = injector.getInstance(ResourceSet)
		injector.injectMembers(this)
		var Resource astData = null
		try {
			astData = resourceSet.getResource(URI.createFileURI(file.absolutePath), true)
		} catch (Exception e) {
			DialogUtil.showErrorAndExit("Invalid file!", "Please select a file with the correct extension!", false)
		}
		return astData
	}

	def createInjector() {
		return new QLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}

	def private validateAST(Resource astData) {
		val validator = createInjector().getInstance(IResourceValidator)

		val issues = validator.validate(astData, CheckMode.ALL, CancelIndicator.NullImpl)

		val errors = issues.filter[it.severity == Severity.ERROR].toList()
		if (!errors.empty) {
			DialogUtil.showErrorAndExit("Errors while parsing the file!", concateIssuesToText(errors), true)
		}

		val warnings = issues.filter[it.severity == Severity.WARNING].toList()
		if (!warnings.empty) {
			DialogUtil.showWarnings("There were some parser warnings...", concateIssuesToText(warnings))
		}
	}

	def private concateIssuesToText(List<Issue> issues) {
		var text = ""
		for (issue : issues) {
			text += issue.toString()
			text += "\n"
		}
		return text
	}

	def getForm(Resource astData) {
		return astData.allContents.head() as Form
	}

	def parseFileAndValidate() {
		val file = openFile()
		val astData = parseFile(file)
		validateAST(astData)
		return astData
	}

	override start(Stage primaryStage) {
		val astData = parseFileAndValidate()
		val dialog = dialogBuilder.buildDialog(getForm(astData))
		dialog.showAndWait()
	}

}

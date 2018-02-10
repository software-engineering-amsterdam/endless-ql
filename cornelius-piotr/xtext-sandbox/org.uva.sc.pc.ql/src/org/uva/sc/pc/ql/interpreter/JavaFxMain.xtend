package org.uva.sc.pc.ql.interpreter

import java.util.ArrayList
import java.util.HashMap
import javafx.application.Application
import javafx.beans.binding.Binding
import javafx.event.Event
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.scene.Scene
import javafx.scene.control.CheckBox
import javafx.scene.control.Control
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.uva.sc.pc.ql.QLangStandaloneSetup
import org.uva.sc.pc.ql.qLang.Expression
import org.uva.sc.pc.ql.qLang.Form
import org.uva.sc.pc.ql.qLang.Question
import org.uva.sc.pc.ql.qLang.TypeBool
import org.uva.sc.pc.ql.qLang.TypeDate
import org.uva.sc.pc.ql.qLang.TypeDecimal
import org.uva.sc.pc.ql.qLang.TypeInteger
import org.uva.sc.pc.ql.qLang.TypeMoney
import org.uva.sc.pc.ql.qLang.TypeString
import org.uva.sc.pc.ql.qLang.util.MissingCaseException

class JavaFxMain extends Application {

	private static var Resource RESOURCE;

	private static val CONTROLS = new HashMap<Object, Control>

	private static val BINDINGS = new ArrayList<Binding>

	override start(Stage primaryStage) {

		val form = RESOURCE.allContents.head as Form
		val rootStage = buildGuiLayout(form)
		registerListeners

		var scene = new Scene(rootStage, 800, 600);
		primaryStage.setTitle(form.name);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	def buildGuiLayout(Form form) {
		val root = new VBox
		form.body.questions.forEach [
			var control = buildControlForQuestion(it)
			root.children.add(control)
		]
		form.blocks.forEach[
			val box = new VBox();
			it.body.questions.forEach [
				val control = buildControlForQuestion(it)
				box.children.add(control)
			]
			val binding = BindingUtil.buildBindingForTypeBoolean(CONTROLS, it.expression)
			box.visibleProperty.bind(binding)
			BINDINGS.add(binding)
			root.children.add(box)
		]
		return root
	}

	def buildControlForQuestion(Question question) {
		val hbox = new HBox
		hbox.children.add(new Label(question.label))
		var Control control
		switch question.type {
			TypeBool:
				control = buildControlForTypeBoolean(question.expression)
			TypeString:
				control = buildControlForTypeString(question.expression)
			TypeInteger:
				control = buildControlForTypeInteger(question.expression)
			TypeDecimal,
			TypeMoney:
				control = buildControlForTypeDecimalAndMoney(question.expression)
			TypeDate:
				control = new DatePicker
			default:
				throw new MissingCaseException
		}
		if (question.expression !== null) {
			control.disable = true
		}
		hbox.children.add(control)
		CONTROLS.put(question.name, control)
		return hbox
	}

	def buildControlForTypeBoolean(Expression expression) {
		var text = new CheckBox
		if (expression !== null) {
			val binding = BindingUtil.buildBindingForTypeBoolean(CONTROLS, expression)
			text.selectedProperty.bind(binding)
			BINDINGS.add(binding)
		}
		return text
	}

	def buildControlForTypeString(Expression expression) {
		var text = new TextField
		if (expression !== null) {
			val binding = BindingUtil.buildBindingForTypeString(CONTROLS, expression)
			text.textProperty.bind(binding)
			BINDINGS.add(binding)
		}
		return text
	}

	def buildControlForTypeInteger(Expression expression) {
		var text = new TextField
		if (expression !== null) {
			val binding = BindingUtil.buildBindingForTypeInteger(CONTROLS, expression)
			text.textProperty.bind(binding)
			BINDINGS.add(binding)
		}
		return text
	}

	def buildControlForTypeDecimalAndMoney(Expression expression) {
		var text = new TextField
		if (expression !== null) {
			val binding = BindingUtil.buildBindingForTypeDecimalAndMoney(CONTROLS, expression)
			text.textProperty.bind(binding)
			BINDINGS.add(binding)
		}
		return text
	}

	def registerListeners() {
		CONTROLS.forEach [ name, control |
			control.addEventHandler(EventType.ROOT, new EventHandler() {
				override handle(Event arg0) {
					invalidateBindings
				}
			})
		]
	}

	def invalidateBindings() {
		BINDINGS.forEach [
			it.invalidate()
		]
	}

	public static def main(String[] args) {

		if (args.size != 1) {
			println("Provide a .ql file as argument!")
			System.exit(0)
		}

		val file = args.get(0);
		val INJECTOR = new QLangStandaloneSetup().createInjectorAndDoEMFRegistration();
		var rs = INJECTOR.getInstance(ResourceSet);
		RESOURCE = rs.getResource(URI.createFileURI(file), true);

		val validator = INJECTOR.getInstance(IResourceValidator);
		var error = false;
		val issues = validator.validate(RESOURCE, CheckMode.ALL, CancelIndicator.NullImpl);
		for (i : issues) {
			println(i);
			if(i.severity == Severity.ERROR) error = true;
		}

		if (error)
			System.exit(0);

		JavaFxMain.launch(args);
	}

}

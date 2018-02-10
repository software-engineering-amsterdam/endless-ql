package org.uva.sc.pc.ql.interpreter

import java.util.HashMap
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.CheckBox
import javafx.scene.control.Control
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
import org.uva.sc.pc.ql.qLang.Form
import javafx.beans.property.BooleanProperty
import javafx.beans.value.ObservableBooleanValue
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.binding.BooleanExpression
import javafx.beans.InvalidationListener
import org.uva.sc.pc.ql.qLang.ExpressionAnd
import org.uva.sc.pc.ql.qLang.ExpressionQuestionRef
import javafx.scene.control.Button
import javafx.event.ActionEvent
import javafx.event.EventHandler

class JavaFxMain extends Application {

	static val INJECTOR = new QLangStandaloneSetup().createInjectorAndDoEMFRegistration();
	private static var Resource r;

	override start(Stage primaryStage) {

		var form = r.allContents.head as Form

		val root = new VBox();

		val widgetMap = new HashMap<String, Control>();

		form.body.questions.forEach [
			var cb = new CheckBox(it.label)
			root.children.add(cb)
			widgetMap.put(it.name, cb)
		]
		val prop = new SimpleBooleanProperty(false);
		form.blocks.forEach [
			val box = new VBox();
//			box.visibleProperty.bind(new ObservableBooleanValue() {
//
//				override getValue() {
//					var bla = it.expression as ExpressionAnd
//					var left = bla.left as ExpressionQuestionRef
//					var right = bla.right as ExpressionQuestionRef
//					var leftCB = widgetMap.get(left.question.name) as CheckBox
//					var rightCB = widgetMap.get(right.question.name) as CheckBox
//					leftCB.selected && rightCB.selected
//				}
//
//				override get() {
//					// throw new UnsupportedOperationException("TODO: auto-generated method stub")
//					var bla = it.expression as ExpressionAnd
//					var left = bla.left as ExpressionQuestionRef
//					var right = bla.right as ExpressionQuestionRef
//					var leftCB = widgetMap.get(left.question.name) as CheckBox
//					var rightCB = widgetMap.get(right.question.name) as CheckBox
//					leftCB.selected && rightCB.selected
//				}
//
//				override addListener(ChangeListener<? super Boolean> arg0) {
//					// throw new UnsupportedOperationException("TODO: auto-generated method stub")
//				}
//
//				override removeListener(ChangeListener<? super Boolean> arg0) {
//					// throw new UnsupportedOperationException("TODO: auto-generated method stub")
//				}
//
//				override addListener(InvalidationListener arg0) {
//					// throw new UnsupportedOperationException("TODO: auto-generated method stub")
//				}
//
//				override removeListener(InvalidationListener arg0) {
//					// throw new UnsupportedOperationException("TODO: auto-generated method stub")
//				}
//
//			})
			it.body.questions.forEach [
				var cb = new CheckBox(it.label)
				box.children.add(cb)
				cb.visibleProperty.bind(prop)
				widgetMap.put(it.name, cb)
			]
			root.children.add(box)
		]
		var button = new Button("Click")
		button.onAction = new EventHandler<ActionEvent>() {
			override handle(ActionEvent e) {
				prop.set(!prop.value)
			}
		}
		root.children.add(button)

		var scene = new Scene(root, 600, 600);

		primaryStage.setTitle(form.name);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static def main(String[] args) {

		if (args.size != 1) {
			println("Provide a .ql file as argument!")
			System.exit(0)
		}

		val file = args.get(0);

		var rs = INJECTOR.getInstance(ResourceSet);
		r = rs.getResource(URI.createFileURI(file), true);

		var validator = INJECTOR.getInstance(IResourceValidator);
		var error = false;
		var issues = validator.validate(r, CheckMode.ALL, CancelIndicator.NullImpl);
		for (i : issues) {
			println(i);
			if(i.severity == Severity.ERROR) error = true;
		}

		if (error)
			System.exit(0);

		launch(args);
	}

}

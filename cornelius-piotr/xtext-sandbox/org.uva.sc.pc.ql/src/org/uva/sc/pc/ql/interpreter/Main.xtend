package org.uva.sc.pc.ql.interpreter

import com.google.common.collect.Lists
import java.awt.FlowLayout
import java.util.ArrayList
import javax.swing.JCheckBox
import javax.swing.JFrame
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.uva.sc.pc.ql.QLangStandaloneSetup
import org.uva.sc.pc.ql.qLang.Form

class Main {
	def static void main(String[] args) {
		var files = Lists.newArrayList("test.ql");
		var injector = new QLangStandaloneSetup().createInjectorAndDoEMFRegistration();
		var rs = injector.getInstance(ResourceSet);
		var resources = new ArrayList<Resource>();
		for (String file : files) {
			var r = rs.getResource(URI.createFileURI(file), true);
			resources.add(r);
		}

		var validator = injector.getInstance(IResourceValidator);
		var error = false;
		for (Resource r : resources) {
			var issues = validator.validate(r, CheckMode.ALL, CancelIndicator.NullImpl);
			for (i : issues) {
				println(i);
				if(i.severity == Severity.ERROR) error = true;
			}
		}
		if(error)
			System.exit(0);
		
		for (Resource r : resources) {
			var form = r.getAllContents().next() as Form;

			var f = new JFrame(form.getName());
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLayout(new FlowLayout());
			
			for(q:  form.body.questions) {
				if(q.getType().equals("boolean")) {
					var box = new JCheckBox(q.getLabel());
					f.getContentPane().add(box);
				}
			}
			
			f.pack();
			f.setVisible(true);

		}
	}
}

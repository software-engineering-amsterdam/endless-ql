package org.uva.sc.pc.ql;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.uva.sc.pc.ql.qLang.Form;
import org.uva.sc.pc.ql.qLang.Model;
import org.uva.sc.pc.ql.qLang.Question;

import com.google.common.collect.Lists;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {
		List<String> files = Lists.newArrayList("/home/meld0/eclipse-workspaces/runtime-EclipseXtext/Bla2/src/Test.ql");
		Injector injector = new QLangStandaloneSetup().createInjectorAndDoEMFRegistration();
		ResourceSet rs = injector.getInstance(ResourceSet.class);
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for (String file : files) {
			Resource r = rs.getResource(URI.createFileURI(file), true);
			resources.add(r);
		}

		IResourceValidator validator = injector.getInstance(IResourceValidator.class);
		for (Resource r : resources) {
			List<Issue> issues = validator.validate(r, CheckMode.ALL, CancelIndicator.NullImpl);
			for (Issue i : issues) {
				System.out.println(i);
			}
		}

		for (Resource r : resources) {
			Model model = (Model) r.getAllContents().next();
			Form form = model.getForms().get(0);

			JFrame f = new JFrame(form.getName());
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLayout(new FlowLayout());
			
			JPanel p = new JPanel();
			for(Question q:  form.getQuestions()) {
				if(q.getType().equals("boolean")) {
					JCheckBox box = new JCheckBox(q.getLabel());
					f.getContentPane().add(box);
				}
			}
			
			f.pack();
			f.setVisible(true);

		}

	}

}

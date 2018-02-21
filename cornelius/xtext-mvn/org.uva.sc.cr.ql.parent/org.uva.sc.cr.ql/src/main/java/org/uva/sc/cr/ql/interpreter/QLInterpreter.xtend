package org.uva.sc.cr.ql.interpreter

import javafx.application.Application

class QLInterpreter {

	public static def main(String[] args) {

		if (args.size != 1) {
			println("Provide a .ql file as argument!")
			System.exit(0)
		}

		Application.launch(QLJavaFxApplication, args);
	}
}

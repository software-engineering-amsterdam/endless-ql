package org.uva.sc.pc.ql.interpreter

import javafx.application.Application

class QLangInterpreter {

	public static def main(String[] args) {

		if (args.size != 1) {
			println("Provide a .ql file as argument!")
			System.exit(0)
		}

		Application.launch(QLangJavaFxApplication, args);
	}
}

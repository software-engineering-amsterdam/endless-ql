package org.uva.sc.pc.qls.interpreter

import javafx.application.Application

class QSLangInterpreter {

	public static def main(String[] args) {

		if (args.size != 1) {
			println("Provide a .qls file as argument!")
			System.exit(0)
		}

		Application.launch(QSLangJavaFxApplication, args);
	}
}

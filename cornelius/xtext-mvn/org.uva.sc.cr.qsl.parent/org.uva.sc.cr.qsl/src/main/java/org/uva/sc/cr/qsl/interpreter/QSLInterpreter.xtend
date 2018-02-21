package org.uva.sc.cr.qsl.interpreter

import javafx.application.Application

class QSLInterpreter {

	public static def main(String[] args) {

		if (args.size != 1) {
			println("Provide a .qsl file as argument!")
			System.exit(0)
		}

		Application.launch(QSLJavaFxApplication, args);
	}
}

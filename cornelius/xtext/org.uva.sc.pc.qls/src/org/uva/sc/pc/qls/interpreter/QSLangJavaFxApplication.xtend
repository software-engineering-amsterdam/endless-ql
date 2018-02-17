package org.uva.sc.pc.qls.interpreter

import org.uva.sc.pc.ql.interpreter.QLangJavaFxApplication
import org.uva.sc.pc.qls.QSLangStandaloneSetup
import org.uva.sc.pc.qls.qSLang.Model

class QSLangJavaFxApplication extends QLangJavaFxApplication {

	override createInjector(){
		new QSLangStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
	
	override getForm(){
		val model = astData.allContents.head as Model
		return model.form
	}
	
}

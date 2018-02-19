package org.uva.sc.cr.qsl.interpreter

import org.uva.sc.cr.qsl.qSL.Model
import org.uva.sc.cr.qsl.QSLStandaloneSetup
import org.uva.sc.cr.ql.interpreter.QLJavaFxApplication

class QSLJavaFxApplication extends QLJavaFxApplication {

	override createInjector(){
		new QSLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
	
	override getForm(){
		val model = astData.allContents.head as Model
		return model.form
	}
	
}

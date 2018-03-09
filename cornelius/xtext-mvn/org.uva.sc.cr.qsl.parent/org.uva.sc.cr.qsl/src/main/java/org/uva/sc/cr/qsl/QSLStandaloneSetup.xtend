package org.uva.sc.cr.qsl

class QSLStandaloneSetup extends QSLStandaloneSetupGenerated {

	def static void doSetup() {
		new QSLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}

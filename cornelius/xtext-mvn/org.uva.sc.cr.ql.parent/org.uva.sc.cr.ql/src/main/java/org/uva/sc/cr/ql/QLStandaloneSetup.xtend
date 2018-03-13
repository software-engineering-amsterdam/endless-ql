package org.uva.sc.cr.ql

class QLStandaloneSetup extends QLStandaloneSetupGenerated {

	def static void doSetup() {
		new QLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}

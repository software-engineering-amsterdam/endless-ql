/*
 * generated by Xtext 2.14.0-SNAPSHOT
 */
package org.uva.sc.cr.qsl


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class QSLStandaloneSetup extends QSLStandaloneSetupGenerated {

	def static void doSetup() {
		new QSLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}

package org.uva.sc.cr.ql.interpreter

import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Dialog
import javafx.scene.control.TextArea
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority

class DialogUtil {

	def static showErrorAndExit(String header, String content, boolean expandable) {
		val alert = new Alert(AlertType.ERROR)
		alert.setTitle("Error")
		alert.setHeaderText(header)

		if (expandable) {
			alert.setContentText("Here are the errors:")
			showExpandableDialog(alert, content)
		} else {
			alert.setContentText(content)
		}

		System.exit(0)
	}

	def static showWarnings(String header, String content) {
		val alert = new Alert(AlertType.WARNING)
		alert.setTitle("Warnings")
		alert.setHeaderText(header)
		alert.setContentText("Here are the warnings:")
		showExpandableDialog(alert, content)
	}

	def static void showExpandableDialog(Dialog dialog, String content) {
		val textArea = new TextArea(content)
		textArea.setEditable(false)
		textArea.setWrapText(true)

		textArea.setMaxWidth(Double.MAX_VALUE)
		textArea.setMaxHeight(Double.MAX_VALUE)
		GridPane.setVgrow(textArea, Priority.ALWAYS)
		GridPane.setHgrow(textArea, Priority.ALWAYS)

		val expContent = new GridPane()
		expContent.setMaxWidth(Double.MAX_VALUE)
		expContent.add(textArea, 0, 1)

		dialog.getDialogPane().setExpandableContent(expContent)
		dialog.showAndWait()
	}

}

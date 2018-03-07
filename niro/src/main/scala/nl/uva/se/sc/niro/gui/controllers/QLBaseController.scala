package nl.uva.se.sc.niro.gui.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.stage.Stage

class QLBaseController {
  @FXML
  def quitApplication(event: ActionEvent): Unit = getActiveStage(event).close()

  protected def getActiveStage(event: ActionEvent): Stage = {
    val closeButton = event.getSource.asInstanceOf[Button]
    closeButton.getScene.getWindow.asInstanceOf[Stage]
  }
}
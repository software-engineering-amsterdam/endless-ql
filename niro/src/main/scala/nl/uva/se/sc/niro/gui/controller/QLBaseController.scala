package nl.uva.se.sc.niro.gui.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.stage.Stage

class QLBaseController {
  private var activeStage: Stage = _

  @FXML
  def quitApplication(event: ActionEvent): Unit = activeStage.close()

  def setActiveStage(stage: Stage): Unit = activeStage = stage
  def getActiveStage(): Stage = activeStage
}
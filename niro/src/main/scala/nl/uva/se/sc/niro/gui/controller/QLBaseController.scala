package nl.uva.se.sc.niro.gui.controller

import javafx.event.ActionEvent
import javafx.fxml.{ FXML, FXMLLoader }
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

class QLBaseController {
  private var activeStage: Stage = _

  case class QLScene[P <: Pane, C <: QLBaseController](pane: P, controller: C)

  @FXML
  def quitApplication(event: ActionEvent): Unit = activeStage.close()

  def setActiveStage(stage: Stage): Unit = activeStage = stage

  def getActiveStage(): Stage = activeStage

  def switchToScene[P <: Pane, C <: QLBaseController](sceneFile: String): QLScene[P, C] = {
    val loader = new FXMLLoader(getClass.getResource(sceneFile))
    val pane: P = loader.load()
    val controller: C = loader.getController()
    controller.setActiveStage(activeStage)
    activeStage.setScene(new Scene(pane))
    QLScene(pane, controller)
  }
}
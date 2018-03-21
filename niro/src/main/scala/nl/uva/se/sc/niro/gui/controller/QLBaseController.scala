package nl.uva.se.sc.niro.gui.controller

import javafx.event.ActionEvent
import javafx.fxml.{ FXML, FXMLLoader }
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

abstract class QLBaseController {
  private var activeStage: Stage = _

  def applicationName(): String

  @FXML
  def quitApplication(event: ActionEvent): Unit = activeStage.close()

  def setActiveStage(stage: Stage): Unit = activeStage = stage

  def getActiveStage: Stage = activeStage

  def switchToScene[P <: Pane, C <: QLBaseController](sceneFile: String, controller: C): P = {
    val loader = new FXMLLoader(getClass.getResource(sceneFile))
    loader.setController(controller)
    val pane: P = loader.load()
    controller.setActiveStage(activeStage)
    activeStage.setScene(new Scene(pane))
    activeStage.setTitle(controller.applicationName())
    pane
  }
}
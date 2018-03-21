package nl.uva.se.sc.niro.gui.application

import java.io.IOException

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import nl.uva.se.sc.niro.gui.controller.{ QLBaseController, QLHomeController }

object QLForms {
  def main(args: Array[String]): Unit = Application.launch(classOf[QLForms], args: _*)
}

class QLForms extends Application {

  @throws[IOException]
  override def start(stage: Stage): Unit = showScene(stage, QLScenes.homeScene, new QLHomeController())

  @throws[IOException]
  def showScene(stage: Stage, sceneFile: String, controller: AnyRef): Unit = {
    stage.setTitle("QL Forms")

    val loader = new FXMLLoader(getClass.getResource(sceneFile))
    loader.setController(controller)
    val actOne: Pane = loader.load()
    loader.getController[QLBaseController]().setActiveStage(stage)
    stage.setScene(new Scene(actOne))

    stage.show()
  }
}

object QLScenes {
  def homeScene: String = "/nl/uva/se/sc/niro/gui/QLHome.fxml"
  def formScene: String = "/nl/uva/se/sc/niro/gui/QLForm.fxml"
}

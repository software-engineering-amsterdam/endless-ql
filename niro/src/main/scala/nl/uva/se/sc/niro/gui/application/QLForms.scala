package nl.uva.se.sc.niro.gui.application

import java.io.IOException

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import nl.uva.se.sc.niro.gui.controller.QLHomeController

object QLForms {
  val HOME_SCREEN = "/nl/uva/se/sc/niro/gui/QLHome.fxml"
  val FORM_SCREEN = "/nl/uva/se/sc/niro/gui/QLForm.fxml"

  def main(args: Array[String]): Unit = Application.launch(classOf[QLForms], args: _*)

  @throws[IOException]
  def openHomeScreen(stage: Stage): Unit = {
    val loader = new FXMLLoader(getClass.getResource(QLForms.HOME_SCREEN))
    val root: Pane = loader.load()
    val scene = new Scene(root)
    loader.getController[QLHomeController]().setActiveStage(stage)
    stage.setTitle("QL Forms")
    stage.setScene(scene)
    stage.show()
  }
}

class QLForms extends Application {
  @throws[IOException]
  override def start(stage: Stage): Unit = QLForms.openHomeScreen(stage)
}

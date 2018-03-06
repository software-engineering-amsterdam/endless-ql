package nl.uva.se.sc.niro.gui.application

import java.io.IOException

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{ Parent, Scene }
import javafx.stage.Stage

object QLForms {
  val HOME_SCREEN = "/nl/uva/se/sc/niro/gui/QLHome.fxml"
  val FORM_SCREEN = "/nl/uva/se/sc/niro/gui/QLForm.fxml"

  def main(args: Array[String]): Unit = Application.launch(classOf[QLForms], args: _*)

  @throws[IOException]
  def openHomeScreen(stage: Stage): Unit = {
    val root = FXMLLoader.load[Parent](classOf[QLForms].getResource(HOME_SCREEN))
    val scene = new Scene(root)
    stage.setTitle("QL Forms")
    stage.setScene(scene)
    stage.show()
  }
}

class QLForms extends Application {
  @throws[IOException]
  override def start(stage: Stage): Unit = QLForms.openHomeScreen(stage)
}

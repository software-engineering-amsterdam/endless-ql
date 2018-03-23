package nl.uva.se.sc.niro.gui.controller

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.stage.{ FileChooser, Stage }
import nl.uva.se.sc.niro.QLFormService
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.converter.GUIModelFactory
import nl.uva.se.sc.niro.model.ql.QLForm
import org.apache.logging.log4j.scala.Logging

class QLHomeController extends QLBaseController with Logging {
  @FXML
  var errorMessages: TextArea = _

  override def applicationName(): String = "QL Forms"

  @FXML
  def openForm(event: ActionEvent): Unit = {
    errorMessages.setVisible(false)
    val selectedFile: File = selectQLFile(getActiveStage)
    if (selectedFile != null) try {
      val formOrErrors: Either[Seq[Errors.Error], QLForm] = QLFormService.importQLSpecification(selectedFile)
      formOrErrors match {
        case Right(form)  => showQLForm(form)
        case Left(errors) => handleErrors(errors)
      }
    } catch {
      case e: IOException => {
        // TODO Improve messages and handling
        errorMessages.setText(s"Oops, please contact the developers:\n\n${e.getMessage}")
        errorMessages.setVisible(true)
        logger.error("Processing a QL/QLS file failed!", e)
      }
    }
  }

  def selectQLFile(stage: Stage): File = {
    val fileChooser = new FileChooser
    fileChooser.setTitle("Select QL form")
    fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("QL Form files", "*.ql"))
    fileChooser.showOpenDialog(stage)
  }

  def showQLForm(form: QLForm): Unit = {
    val controller = new QLFormController(this, form, GUIModelFactory.makeFrom(form))
    switchToScene(QLScenes.formScene, controller)
    controller.initializeForm()
  }

  def handleErrors(errors: Seq[Errors.Error]): Unit = {
    errorMessages.setText(errors.toString)
    errorMessages.setVisible(true)
  }
}

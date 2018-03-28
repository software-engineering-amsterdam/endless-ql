package nl.uva.se.sc.niro.gui.controller.ql

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{ Alert, ButtonType, TextArea }
import javafx.stage.{ FileChooser, Stage }
import nl.uva.se.sc.niro.PrettyPrinter.{ ErrorsCanPrettyPrint, WarningCanPrettyPrint }
import nl.uva.se.sc.niro.QLFormService
import nl.uva.se.sc.niro.errors.{ Errors, Warning }
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.converter.QLToGUIModelBridge
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
      case e: IOException =>
        // TODO Improve messages and handling
        errorMessages.setText(s"Oops, please contact the developers:\n\n${e.getMessage}")
        errorMessages.setVisible(true)
        logger.error("Processing a QL file failed!", e)
    }
  }

  def selectQLFile(stage: Stage): File = {
    val fileChooser = new FileChooser
    fileChooser.setTitle("Select QL form")
    fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("QL Form files", "*.ql"))
    fileChooser.showOpenDialog(stage)
  }

  def showQLForm(form: QLForm): Unit = {
    val controller = new QLFormController(this, form, QLToGUIModelBridge.convertForm(form))
    if (form.warnings.nonEmpty) showWarning(form.warnings)
    switchToScene(QLScenes.formScene, controller)

    controller.initializeForm()
  }

  def showWarning(warnings: Seq[Warning]): Unit = {
    val alert = new Alert(AlertType.WARNING, s"${warnings.map(_.prettyPrint).mkString("\n")}", ButtonType.OK)
    alert.setTitle("Warning")
    alert.showAndWait()
  }

  def handleErrors(errors: Seq[Errors.Error]): Unit = {
    errorMessages.setText(errors.map(_.prettyPrint).mkString("\n"))
    errorMessages.setVisible(true)
  }
}

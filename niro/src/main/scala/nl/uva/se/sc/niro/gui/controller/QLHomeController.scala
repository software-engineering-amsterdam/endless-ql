package nl.uva.se.sc.niro.gui.controller

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import javafx.stage.{ FileChooser, Stage }
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import nl.uva.se.sc.niro.{ QLFormService, QLStylesheetService }
import org.apache.logging.log4j.scala.Logging

class QLHomeController extends QLBaseController with Logging {
  @FXML
  var errorMessages: TextArea = _

  @FXML
  def openForm(event: ActionEvent): Unit = {
    errorMessages.setVisible(false)
    val selectedFile: File = selectQLFile(getActiveStage())
    if (selectedFile != null) try {
      val formOrErrors: Either[Seq[Errors.Error], QLForm] = QLFormService.importQLSpecification(selectedFile)
      formOrErrors match {
        case Right(form) => {
          val stylesheetOrErrors: Either[Seq[Errors.Error], Option[QLStylesheet]] =
            QLStylesheetService.importQLStylesheetSpecification(form, new File(selectedFile.toString + "s"))
          stylesheetOrErrors match {
            case Right(stylesheet) => handleSuccess(form, stylesheet)
            case Left(errors)      => handleErrors(errors)
          }
        }
        case Left(errors) => handleErrors(errors)
      }
    } catch {
      case e: IOException => {
        // TODO Improve message and handling
        errorMessages.setText(s"Oops, please contact the developers:\n\n${e.getMessage}")
        logger.error("Processing a QL/QLS file failed!", e)
        errorMessages.setVisible(true)
      }
    }
  }

  private def selectQLFile(stage: Stage): File = {
    val fileChooser = new FileChooser
    fileChooser.setTitle("Select QL form")
    fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("QL Form files", "*.ql"))
    fileChooser.showOpenDialog(stage)
  }

  private def handleSuccess(form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    showQLForm(form, stylesheet)
  }

  private def handleErrors(errors: Seq[Errors.Error]): Unit = {
    errorMessages.setText(errors.toString)
    errorMessages.setVisible(true)
  }

  private def showQLForm(form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    val formScene: QLScene[BorderPane, QLFormController] = switchToScene(QLScenes.getFormSceneFileName())
    formScene.controller.initializeForm(form, stylesheet)
  }
}

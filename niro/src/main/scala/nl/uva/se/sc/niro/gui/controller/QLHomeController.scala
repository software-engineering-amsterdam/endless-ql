package nl.uva.se.sc.niro.gui.controller

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.{ FXML, FXMLLoader }
import javafx.scene.Scene
import javafx.scene.control.TextArea
import javafx.scene.layout.Pane
import javafx.stage.{ FileChooser, Stage }
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.gui.application.QLForms
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
            case Right(stylesheet) => handleSuccess(getActiveStage(), form, stylesheet)
            case Left(errors)      => handleErrors(errors)
          }
        }
        case Left(errors) => handleErrors(errors)
      }
    } catch {
      case e: IOException => {
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

  private def handleSuccess(stage: Stage, form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    val formScene = createSceneForForm(form, stylesheet)
    stage.setScene(formScene)
  }

  private def handleErrors(errors: Seq[Errors.Error]): Unit = {
    errorMessages.setText(errors.toString)
    errorMessages.setVisible(true)
  }

  @throws[IOException]
  private def createSceneForForm(form: QLForm, stylesheet: Option[QLStylesheet]) = {
    val loader = new FXMLLoader(getClass.getResource(QLForms.FORM_SCREEN))
    val root: Pane = loader.load()
    loader.getController[QLFormController]().setActiveStage(getActiveStage())
    loader.getController[QLFormController]().initializeForm(form, stylesheet)
    new Scene(root)
  }
}

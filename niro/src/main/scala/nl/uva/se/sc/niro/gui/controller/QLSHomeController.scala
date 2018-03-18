package nl.uva.se.sc.niro.gui.controller

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.FXML
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import nl.uva.se.sc.niro.{ QLFormService, QLStylesheetService }

class QLSHomeController extends QLHomeController {

  @FXML
  override def openForm(event: ActionEvent): Unit = {
    errorMessages.setVisible(false)
    val selectedFile: File = selectQLFile(getActiveStage)
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
        // TODO Improve messages and handling
        errorMessages.setText(s"Oops, please contact the developers:\n\n${e.getMessage}")
        errorMessages.setVisible(true)
        logger.error("Processing a QL/QLS file failed!", e)
      }
    }
  }

  def handleSuccess(form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    showQLForm(form, stylesheet)
  }

  def showQLForm(form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    val formController = new QLSFormController()
    switchToScene(QLScenes.formScene, formController)
    // FIXME
//    formController.initializeForm(form, stylesheet)
  }
}

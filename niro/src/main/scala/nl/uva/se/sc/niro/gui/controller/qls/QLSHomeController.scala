package nl.uva.se.sc.niro.gui.controller.qls

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.FXML
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.controller.ql.QLHomeController
import nl.uva.se.sc.niro.gui.converter.{ QLSToGUIModelBridge, QLToGUIModelBridge }
import nl.uva.se.sc.niro.model.gui.ql.GUIForm
import nl.uva.se.sc.niro.model.gui.qls.GUIStylesheet
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import nl.uva.se.sc.niro.{ QLFormService, QLStylesheetService }

class QLSHomeController extends QLHomeController {
  override def applicationName(): String = "QLS Forms"

  @FXML
  override def openForm(event: ActionEvent): Unit = {
    // TODO reuse code from QLHomeController??
    errorMessages.setVisible(false)
    val selectedFile: File = selectQLFile(getActiveStage)
    if (selectedFile != null) try {
      val formOrErrors: Either[Seq[Errors.Error], QLForm] = QLFormService.importQLSpecification(selectedFile)
      formOrErrors match {
        case Right(form) => {
          val stylesheetOrErrors: Either[Seq[Errors.Error], Option[QLStylesheet]] =
            QLStylesheetService.importQLStylesheetSpecification(form, new File(selectedFile.toString + "s"))
          stylesheetOrErrors match {
            case Right(possibleStylesheet) =>
              possibleStylesheet match {
                case Some(stylesheet) => showQLSForm(form, stylesheet)
                case None             => showQLForm(form) // Fall back to pure QL
              }
            case Left(errors) => handleErrors(errors)
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

  def showQLSForm(model: QLForm, stylesheet: QLStylesheet): Unit = {
    val guiForm: GUIForm = QLToGUIModelBridge.convertForm(model)
    val guiStyle: GUIStylesheet = QLSToGUIModelBridge.convertStylesheet(stylesheet)
    val formController = new QLSFormController(this, model, guiForm, guiStyle)
    switchToScene(QLScenes.formScene, formController)
    formController.initializeForm()
  }
}

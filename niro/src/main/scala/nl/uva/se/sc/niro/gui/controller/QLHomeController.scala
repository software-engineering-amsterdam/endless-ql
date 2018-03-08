package nl.uva.se.sc.niro.gui.controller

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.{ FXML, FXMLLoader }
import javafx.scene.control.TextArea
import javafx.scene.{ Parent, Scene }
import javafx.stage.{ FileChooser, Stage }
import nl.uva.se.sc.niro.QLFormService
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.model.QLForm

class QLHomeController extends QLBaseController {
  @FXML
  private var errorMessages: TextArea = _

  @FXML
  def openForm(event: ActionEvent): Unit = {
    val fileChooser = new FileChooser
    fileChooser.setTitle("Select QL form")
    fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("QL Form files", "*.ql"))
    val stage = getActiveStage(event)
    val selectedFile: File = fileChooser.showOpenDialog(stage)
    if (selectedFile != null) try {
      val formOrErrors: Either[Seq[Errors.Error], QLForm] = QLFormService.importQLSpecification(selectedFile)
      formOrErrors match {
        case Right(form)  => handleSuccess(stage, form)
        case Left(errors) => handleErrors(errors)
      }
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  private def handleSuccess(stage: Stage, form: QLForm) = {
    val formScene = createSceneForForm(form)
    stage.setScene(formScene)
  }

  private def handleErrors(errors: Seq[Errors.Error]) = {
    errorMessages.setText(errors.toString)
    errorMessages.setVisible(true)
  }

  @throws[IOException]
  private def createSceneForForm(form: QLForm) = {
    val loader = new FXMLLoader(getClass.getResource(QLForms.FORM_SCREEN))
    val root: Parent = loader.load[Parent]
    loader.getController.asInstanceOf[QLFormController].populateForm(form)
    new Scene(root)
  }
}

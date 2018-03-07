package nl.uva.se.sc.niro.gui.controllers

import java.io.{ File, IOException }

import javafx.event.ActionEvent
import javafx.fxml.{ FXML, FXMLLoader }
import javafx.scene.control.TextArea
import javafx.scene.{ Parent, Scene }
import javafx.stage.FileChooser
import nl.uva.se.sc.niro.QLFormService
import nl.uva.se.sc.niro.errors.Errors
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.model.QLForm

class QLHomeController extends QLBaseController {
  @FXML
  private var errorMessages: TextArea = null

  @FXML
  def openForm(event: ActionEvent): Unit = {
    val fileChooser = new FileChooser
    fileChooser.setTitle("Select QL form")
    fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("QL Form files", "*.ql"))
    val stage = getActiveStage(event)
    val selectedFile: File = fileChooser.showOpenDialog(stage)
    if (selectedFile != null) try {
      val formOrErrors: Either[QLForm, Seq[Errors.Error]] = QLFormService.importQLSpecification(selectedFile)
      formOrErrors match {
        case Left(form) =>
          val formScene = createSceneForForm(form)
          stage.setScene(formScene)
        case Right(errors) =>
          errorMessages.setText(errors.toString)
          errorMessages.setVisible(true)
      }
    } catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

  @throws[IOException]
  private def createSceneForForm(form: QLForm) = {
    val loader = new FXMLLoader(getClass.getResource(QLForms.FORM_SCREEN))
    val root: Parent = loader.load[Parent]
    loader.getController.asInstanceOf[QLFormController].populateForm(form)
    new Scene(root)
  }
}

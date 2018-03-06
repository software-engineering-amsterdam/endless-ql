package nl.uva.se.sc.niro.gui.controllers

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.{ FXML, FXMLLoader }
import javafx.scene.control.TextArea
import javafx.scene.{ Parent, Scene }
import javafx.stage.FileChooser
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.gui.util.ErrorUtil
import nl.uva.se.sc.niro.model.QLForm
import nl.uva.se.sc.niro.parser.QLFormParser
import org.antlr.v4.runtime.CharStreams

class QLHomeController extends QLBaseController {
  @FXML
  private val errorMessages: TextArea = null

  @FXML
  def openForm(event: ActionEvent): Unit = {
    val fileChooser = new FileChooser
    fileChooser.setTitle("Select QL form")
    fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("QL Form files", "*.ql"))
    val stage = getActiveStage(event)
    val selectedFile = fileChooser.showOpenDialog(stage)
    if (selectedFile != null) try {
      val form = QLFormParser.parse(CharStreams.fromFileName(selectedFile.getAbsolutePath))
      if (QLFormParser.getParseErrors.isEmpty) {
        val formScene = createSceneForForm(form)
        stage.setScene(formScene)
      } else {
        errorMessages.setText(ErrorUtil.toString(QLFormParser.getParseErrors))
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
    val root = loader.load.asInstanceOf[Parent]
    loader.getController.asInstanceOf[QLFormController].populateForm(form)
    new Scene(root)
  }
}

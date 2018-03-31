package nl.uva.se.sc.niro.gui.controller.qls

import java.io.{ File, IOException }

import javafx.stage.Stage
import nl.uva.se.sc.niro.QLStylesheetFacade
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.controller.ql.QLHomeController
import nl.uva.se.sc.niro.gui.converter.{ QLSToGUIModelBridge, QLToGUIModelBridge }
import nl.uva.se.sc.niro.model.gui.ql.GUIForm
import nl.uva.se.sc.niro.model.gui.qls.GUIStylesheet
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet

class QLSHomeController extends QLHomeController {
  private var qlFile: File = _
  override def applicationName(): String = "QLS Forms"

  override def selectQLFile(stage: Stage): File = {
    qlFile = super.selectQLFile(stage)
    qlFile
  }

  override def showForm(form: QLForm): Unit = {
    try {
      QLStylesheetFacade.importQLStylesheetSpecification(form, new File(qlFile.toString + "s")) match {
        case Right(possibleStylesheet) =>
          possibleStylesheet match {
            case Some(stylesheet) => showForm(form, stylesheet)
            case None             => super.showForm(form) // Fall back to pure QL
          }
        case Left(errors) => handleErrors(errors)
      }
    } catch {
      case e: IOException =>
        errorMessages.setText(s"Reading the QLS file failed.:\n\n${e.getMessage}")
        errorMessages.setVisible(true)
        logger.error("QLS Reading Error!", e)
    }
  }

  def showForm(model: QLForm, stylesheet: QLStylesheet): Unit = {
    val guiForm: GUIForm = QLToGUIModelBridge.convertForm(model)
    val guiStyle: GUIStylesheet = QLSToGUIModelBridge.convertStylesheet(stylesheet)
    val formController = new QLSFormController(this, model, guiForm, guiStyle)
    switchToScene(QLScenes.formScene, formController)
    formController.initializeForm()
  }
}

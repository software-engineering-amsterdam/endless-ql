package nl.uva.se.sc.niro.qls.controller

import java.io.{ File, IOException }

import javafx.stage.Stage
import nl.uva.se.sc.niro.ql.application.QLScenes
import nl.uva.se.sc.niro.ql.controller.QLHomeController
import nl.uva.se.sc.niro.ql.model.QLModelBridge
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.ql.model.gui.Form
import nl.uva.se.sc.niro.qls.model.QLSToGUIModelBridge
import nl.uva.se.sc.niro.qls.model.ast.QLStylesheet
import nl.uva.se.sc.niro.qls.model.gui.GUIStylesheet
import nl.uva.se.sc.niro.qls.parser.QLStylesheetFacade

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
    val guiForm: Form = QLModelBridge.convertForm(model)
    val guiStyle: GUIStylesheet = QLSToGUIModelBridge.convertStylesheet(stylesheet)
    val formController = new QLSFormController(this, model, guiForm, guiStyle)
    switchToScene(QLScenes.formScene, formController)
    formController.initializeForm()
  }
}

package nl.uva.se.sc.niro.gui.controller

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.{ Label, Pagination }
import nl.uva.se.sc.niro.gui.control.Component
import nl.uva.se.sc.niro.gui.converter.GUIModelFactory
import nl.uva.se.sc.niro.gui.factory.PageFactory
import nl.uva.se.sc.niro.model.gui.GUIForm
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet
import nl.uva.se.sc.niro.util.StringUtil

class QLSFormController(homeController: QLHomeController, override val form: QLForm, val stylesheet: QLStylesheet)
    extends QLFormController(homeController, form) {

  private val pagination = new Pagination()
  private val pageName: Label = new Label("Page Name")
  override def applicationName(): String = "QLS Forms"

  @FXML
  def initialize(): Unit = {
    applyStylingToPageName()
    topBox.getChildren.add(pageName)
  }

  def applyStylingToPageName(): Unit = {
    pageName.setPadding(new Insets(10.0, 0.0, 0.0, 0.0))
    pageName.getStyleClass.add("-fx-font-size: 18pt;")
  }

  override def initializeForm(): Unit = {
    guiForm = GUIModelFactory.makeFrom(form)

    pagination.setPageCount(stylesheet.pages.size)
    pagination.setPadding(new Insets(00.0, 20.0, 00.0, 20.0))

    pagination.setPageFactory(new PageFactory(this, GUIModelFactory.makeFrom(stylesheet)))
    pagination
      .currentPageIndexProperty()
      .addListener(new ChangeListener[Number] {
        override def changed(observable: ObservableValue[_ <: Number], oldValue: Number, newValue: Number): Unit = {
          evaluateAnswers()
          updateView()
        }
      })

    questionArea.setContent(pagination)
    questionArea.setFitToHeight(true)
    questionArea.setFitToWidth(true)

    getActiveStage.setTitle("QLS forms")
    formName.setText(guiForm.name)
  }

  override def updateView(): Unit = {
    updatePageTitle()
    super.updateView()
  }

  private def updatePageTitle(): Unit = {
    val pageToShow = stylesheet.pages(pagination.getCurrentPageIndex)
    pageName.setText(StringUtil.addSpaceOnCaseChange(pageToShow.name))
  }

  def setQuestionControls(questionComponents: Seq[Component[_]]): Unit = this.questionComponents = questionComponents
  def getQuestionControls: Seq[Component[_]] = questionComponents
  def getGUIForm: GUIForm = guiForm
}

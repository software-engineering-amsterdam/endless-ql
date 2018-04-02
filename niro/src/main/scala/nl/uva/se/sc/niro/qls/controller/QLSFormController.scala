package nl.uva.se.sc.niro.qls.controller

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.{ Label, Pagination }
import nl.uva.se.sc.niro.ql.controller.{ QLFormController, QLHomeController }
import nl.uva.se.sc.niro.ql.model.ast.QLForm
import nl.uva.se.sc.niro.ql.model.gui.Form
import nl.uva.se.sc.niro.ql.view.component.Component
import nl.uva.se.sc.niro.qls.model.gui.Stylesheet
import nl.uva.se.sc.niro.qls.view.QLSPageFactoryBuilder
import nl.uva.se.sc.niro.qls.view.component.QLSComponentFactoryBuilder
import nl.uva.se.sc.niro.qls.view.widget.QLSWidgetFactory
import nl.uva.se.sc.niro.util.StringUtil

class QLSFormController(homeController: QLHomeController, model: QLForm, guiForm: Form, stylesheet: Stylesheet)
    extends QLFormController(homeController, model, guiForm) {

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
    val componentFactory = QLSComponentFactoryBuilder
      .buildWithFontSize()
      .buildWithFontType()
      .buildWithColor()
      .buildWithWidth()
      .buildWithIsReadonly()
      .buildWithBind()
      .buildWith(this)
      .buildWith(new QLSWidgetFactory())
      .build()

    val pageFactory = new QLSPageFactoryBuilder()
      .buildWith(guiForm)
      .buildWith(stylesheet)
      .buildWith(componentFactory)
      .buildWith(this)
      .build

    pagination.setPageCount(stylesheet.pages.size)
    pagination.setPadding(new Insets(00.0, 20.0, 00.0, 20.0))
    pagination.setPageFactory(pageFactory)

    questionArea.setContent(pagination)
    questionArea.setFitToHeight(true)
    questionArea.setFitToWidth(true)

    getActiveStage.setTitle("QLS forms")
    formName.setText(guiForm.name)

    evaluateAnswers()
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
}

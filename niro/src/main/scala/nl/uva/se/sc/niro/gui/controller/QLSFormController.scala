package nl.uva.se.sc.niro.gui.controller

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.{ Label, Pagination }
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.converter.{ GUIModelFactory, StyleDecorator }
import nl.uva.se.sc.niro.gui.factory.QLSComponentFactory
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet

class QLSFormController(homeController: QLHomeController) extends QLFormController(homeController) {
  private var stylesheet: QLStylesheet = _
  private val pageName: Label = new Label("Page Name")
  private val pagination = new Pagination()

  override def applicationName(): String = "QLS Forms"

  @FXML
  def initialize(): Unit = {
    applyStylingToPageName()
    topBox.getChildren.add(pageName)

    applyStylingToNavigationBar()
  }

  def applyStylingToPageName(): Unit = {
    pageName.setPadding(new Insets(10.0, 0.0, 0.0, 0.0))
    pageName.getStyleClass.add("-fx-font-size: 18pt;")
  }

  def applyStylingToNavigationBar(): Unit = {
    pagination.setPadding(new Insets(0.0, 10.0, 0.0, 10.0))
  }

  def initializeForm(form: QLForm, stylesheet: QLStylesheet): Unit = {
    this.qlForm = form
    this.stylesheet = stylesheet

    pagination.setPageCount(stylesheet.pages.size)
    // TODO move to own file!
    class PageFactory() extends Callback[Integer, Node]() {
      override def call(pageNumber: Integer): Node = {
        pageName.setText(pageNumber.toString)
        val questionsOnPage = new VBox()
        // TODO component construction goes here!
//         stylesheet.pages(pageNumber).sections.flatMap(_.questions.map(QLSComponentFactory.make(_)))
        questionsOnPage
      }
    }
    pagination.setPageFactory(new PageFactory())

    guiForm = StyleDecorator.applyStyle(GUIModelFactory.makeFrom(qlForm), stylesheet)
    questions = guiForm.questions.map(QLSComponentFactory.make)
    questions.foreach(_.addComponentChangedListener(this))
    questionArea.setContent(pagination)

    getActiveStage.setTitle("QLS forms")
    formName.setText(guiForm.name)

    evaluateAnswers()
    updateView()
  }

}

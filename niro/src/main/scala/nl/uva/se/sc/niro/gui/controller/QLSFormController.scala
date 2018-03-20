package nl.uva.se.sc.niro.gui.controller

import javafx.beans.value.{ ChangeListener, ObservableValue }
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.{ Label, Pagination }
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.converter.GUIModelFactory
import nl.uva.se.sc.niro.gui.factory.QLSComponentFactory
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.QLStylesheet

import scala.collection.JavaConverters
import scala.collection.mutable.ArrayBuffer

class QLSFormController(homeController: QLHomeController, override val form: QLForm, val stylesheet: QLStylesheet)
    extends QLFormController(homeController, form) {
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

    val pagination = new Pagination(stylesheet.pages.size)
    pagination.setPadding(new Insets(00.0, 20.0, 00.0, 20.0))

    // TODO extract into own file
    class PageFactory(changedListener: ComponentChangedListener) extends Callback[Integer, Node]() {
      override def call(pageNumber: Integer): Node = {
        pageName.setText(stylesheet.pages(pageNumber).name)
        val questionsOnPage = new VBox()
        val children = ArrayBuffer[Node]()
        questions = stylesheet.pages(pageNumber).sections.flatMap(section => {
          children.append(new Label(s"  -- ${section.name} --  "))
          section.questions.flatMap(question => {
            guiForm.questions.filter(_.id == question.name).map(question => {
              val component = QLSComponentFactory.make(question)
              children.append(component)
              component
            })
          })
        })
        questions.foreach(_.addComponentChangedListener(changedListener))
        questionsOnPage.getChildren.addAll(JavaConverters.bufferAsJavaList(children))

        evaluateAnswers()
        updateView()

        questionsOnPage
      }
    }
    pagination.setPageFactory(new PageFactory(this))
    pagination.currentPageIndexProperty().addListener(new ChangeListener[Number] {
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

}

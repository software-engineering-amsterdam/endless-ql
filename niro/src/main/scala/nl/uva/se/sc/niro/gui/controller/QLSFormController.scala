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
import nl.uva.se.sc.niro.model.gui.{ GUIQuestion, QLSGUIQuestion, Styling }
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.qls.{ QLSWidgetType, QLStylesheet }
import nl.uva.se.sc.niro.util.StringUtil

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
      private val children = ArrayBuffer[Node]()

      override def call(pageNumber: Integer): Node = {
        children.clear()
        val pageToShow = stylesheet.pages(pageNumber)
        pageName.setText(StringUtil.addSpaceOnCaseChange(pageToShow.name))

        questions = pageToShow.sections.flatMap(section => {
          children.append(new Label(s"  -- ${section.name} --  "))
          section.questions.flatMap(question => {
            guiForm.questions.filter(_.id == question.name).map(makeStyledComponent(_, question.widgetType))
          })
        })

        questions.foreach(_.addComponentChangedListener(changedListener))

        // This is the node that is being returned
        val questionsOnPage = new VBox()
        questionsOnPage.getChildren.addAll(JavaConverters.bufferAsJavaList(children))

        evaluateAnswers()
        updateView()

        questionsOnPage
      }

      def applyWidgetType(question: GUIQuestion, widgetType: Option[QLSWidgetType]): GUIQuestion = {
        if (widgetType.isDefined) QLSGUIQuestion(question, Styling(widgetType.get)) else question
      }

      private def makeStyledComponent(question: GUIQuestion, widgetType: Option[QLSWidgetType]) = {
        val component = QLSComponentFactory.make(applyWidgetType(question, widgetType))
        children.append(component)
        component

      }
    }

    pagination.setPageFactory(new PageFactory(this))
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

}

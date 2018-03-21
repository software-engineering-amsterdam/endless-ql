package nl.uva.se.sc.niro.gui.factory

import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.controller.QLSFormController
import nl.uva.se.sc.niro.model.gui.{ GUIQuestion, QLSGUIQuestion, Styling }
import nl.uva.se.sc.niro.model.qls.QLSWidgetType

import scala.collection.JavaConverters
import scala.collection.mutable.ArrayBuffer

// TODO change parameter type (and name)
class PageFactory(formController: QLSFormController) extends Callback[Integer, Node]() {
  // TODO Refactor so it is not a instance variable anymore (move into method 'call')
  private val children = ArrayBuffer[Node]()

  override def call(pageNumber: Integer): Node = {
    children.clear()
    val pageToShow = formController.stylesheet.pages(pageNumber)

    formController.setQuestionControls(pageToShow.sections.flatMap(section => {
      children.append(new Label(s"  -- ${section.name} --  "))
      section.questions.flatMap(question => {
        formController.getGUIForm.questions.filter(_.id == question.name).map(makeStyledComponent(_, question.widgetType))
      })
    }))

    formController.getQuestionControls.foreach(_.addComponentChangedListener(formController))

    // This is the node that is being returned
    val questionsOnPage = new VBox()
    questionsOnPage.getChildren.addAll(JavaConverters.bufferAsJavaList(children))

    formController.evaluateAnswers()
    formController.updateView()

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

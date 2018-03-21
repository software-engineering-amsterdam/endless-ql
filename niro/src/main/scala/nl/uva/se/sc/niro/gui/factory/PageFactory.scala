package nl.uva.se.sc.niro.gui.factory

import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.controller.QLSFormController
import nl.uva.se.sc.niro.model.gui.{ GUIQuestion, GUIStylesheet, QLSGUIQuestion, Styling }

import scala.collection.JavaConverters
import scala.collection.mutable.ArrayBuffer

// TODO change parameter type (and name)
class PageFactory(formController: QLSFormController, stylesheet: GUIStylesheet) extends Callback[Integer, Node]() {
  // TODO Refactor so it is not a instance variable anymore (move into method 'call')
  private val children = ArrayBuffer[Node]()

  override def call(pageNumber: Integer): Node = {
    children.clear()
    val pageToShow = stylesheet.pages(pageNumber)

    formController.setQuestionControls(pageToShow.sections.flatMap(section => {
      children.append(new Label(s"  -- ${section.name} --  "))
      section.questions.flatMap(question => {
        formController.getGUIForm.questions.filter(_.id == question.name).map(makeStyledComponent(_, question.style))
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

  // TODO Make this method not use 'children'
  private def makeStyledComponent(question: GUIQuestion, style: Styling) = {
    val component = QLSComponentFactory.make(QLSGUIQuestion(question, style))
    children.append(component)
    component

  }
}

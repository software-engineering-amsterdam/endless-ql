package nl.uva.se.sc.niro.gui.factory

import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.controller.QLSFormController
import nl.uva.se.sc.niro.model.gui._

class PageFactory(formController: QLSFormController, form: GUIForm, stylesheet: GUIStylesheet)
    extends Callback[Integer, Node]() {

  override def call(pageNumber: Integer): Node = {
    val page = new VBox()
    val pageToShow = stylesheet.pages(pageNumber)

    val components = pageToShow.sections.flatMap(section => {
      addSectionHeader(page, section)

      section.questionStyles.flatMap(questionStyle => {
        form
          .collectQuestionOnName(questionStyle.name)
          .map(question => {
            val component = QLSComponentFactory(formController).make(QLSGUIQuestion(question, questionStyle.style))
            question.component = Some(component)
            page.getChildren.add(component)
            component
          })
      })
    })

    formController.setQuestionControls(components)
    formController.evaluateAnswers()
    formController.updateView()

    page
  }

  private def addSectionHeader(questionsOnPage: VBox, section: GUISection) = {
    questionsOnPage.getChildren.add(new Label(s"  -- ${section.name} --  "))
  }

}

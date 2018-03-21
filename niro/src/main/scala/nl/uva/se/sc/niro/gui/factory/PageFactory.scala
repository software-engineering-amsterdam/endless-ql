package nl.uva.se.sc.niro.gui.factory

import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.controller.QLSFormController
import nl.uva.se.sc.niro.model.gui._
import nl.uva.se.sc.niro.model.ql._

class PageFactory(formController: QLSFormController, form: GUIForm, stylesheet: GUIStylesheet)
    extends Callback[Integer, Node]() {

  // TODO REFACTOR!!!!!!!!!!
  val defaultStyles: Map[String, GUIStyle] = Map(
    "string" -> GUIDefaultStyle(),
    "boolean" -> GUIDefaultStyle(),
    "date" -> GUIDefaultStyle(),
    "integer" -> GUIDefaultStyle(),
    "decimal" -> GUIDefaultStyle(),
    "money" -> GUIDefaultStyle()
  )

  // TODO Move to better place
  def bla(question: GUIQuestion): String = question.answerType match {
    case BooleanType => "boolean"
    case DateType    => "date"
    case StringType  => "string"
    case IntegerType => "integer"
    case DecimalType => "decimal"
    case MoneyType   => "money"
  }

  override def call(pageNumber: Integer): Node = {
    val page = new VBox()
    val pageToShow = stylesheet.pages(pageNumber)

    val defaultPageStyles = defaultStyles ++ stylesheet.defaultStyles ++ pageToShow.defaultStyles

    val components = pageToShow.sections.flatMap(section => {
      addSectionHeader(page, section)
      val defaultSectionStyles = defaultPageStyles ++ section.defaultStyles

      section.questions.flatMap(styledQuestion => {
        form
          .collectQuestionOnName(styledQuestion.name)
          .map(question => {
            val styleToUse = styledQuestion.style.getOrElse(defaultSectionStyles(bla(question)))
            val component = QLSComponentFactory(formController).make(QLSGUIQuestion(question, styleToUse))
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

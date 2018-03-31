package nl.uva.se.sc.niro.gui.component.qls

import cats.Semigroup
import cats.instances.all._
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.gui.component.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.gui.controller.qls.QLSFormController
import nl.uva.se.sc.niro.model.gui.ql.GUIForm
import nl.uva.se.sc.niro.model.gui.qls.{ GUIStyling, _ }
import nl.uva.se.sc.niro.model.ql._

class QLSPageFactory(
    formController: QLSFormController,
    form: GUIForm,
    stylesheet: GUIStylesheet,
    componentFactory: ComponentFactory)
    extends Callback[Integer, Node]() {

  val defaultStyles: Map[AnswerType, GUIStyling] = Map(
    StringType -> GUIStyling(),
    BooleanType -> GUIStyling(),
    DateType -> GUIStyling(),
    IntegerType -> GUIStyling(),
    DecimalType -> GUIStyling(),
    MoneyType -> GUIStyling()
  )

  override def call(pageNumber: Integer): Node = {
    val page = new VBox()
    val pageToShow = stylesheet.pages(pageNumber)

    makePage(page, pageToShow)
  }

  def makePage(page: VBox, pageToShow: GUIPage): VBox = {
    val defaultPageStyles = mergeStyles(mergeStyles(defaultStyles, stylesheet.defaultStyles), pageToShow.defaultStyles)

    val components = pageToShow.sections.flatMap(section => {
      makeSection(page, section, defaultPageStyles)
    })

    formController.setQuestionControls(components)

    page
  }

  def makeSection(page: VBox, section: GUISection, defaultStyles: Map[AnswerType, GUIStyling]): Seq[Component[_]] = {
    val defaultSectionStyles = mergeStyles(defaultStyles, section.defaultStyles)

    addSectionHeader(page, section)

    section.statements.flatMap({
      case GUIQuestionStyling(name, style) =>
        form
          .collectQuestionOnName(name)
          .map(question => {
            val styleToUse = defaultSectionStyles(question.answerType) ++ style
            val component = componentFactory.make(QLSGUIQuestion(question, styleToUse))
            question.component = Some(component)
            page.getChildren.add(component)
            component
          })
      case section: GUISection => makeSection(page, section, defaultStyles)
    })
  }

  def addSectionHeader(questionsOnPage: VBox, section: GUISection): Boolean = {
    questionsOnPage.getChildren.add(new Label(s"  -- ${section.name} --  "))
  }

  def mergeStyles(left: Map[AnswerType, GUIStyling], right: Map[AnswerType, GUIStyling]): Map[AnswerType, GUIStyling] =
    Semigroup[Map[AnswerType, GUIStyling]].combine(left, right)

}

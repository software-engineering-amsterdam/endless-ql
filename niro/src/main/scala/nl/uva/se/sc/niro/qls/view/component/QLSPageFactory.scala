package nl.uva.se.sc.niro.qls.view.component

import cats.Semigroup
import cats.instances.all._
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.util.Callback
import nl.uva.se.sc.niro.ql.model.ast._
import nl.uva.se.sc.niro.ql.model.gui.Form
import nl.uva.se.sc.niro.ql.view.component.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.qls.controller.QLSFormController
import nl.uva.se.sc.niro.qls.model.gui.{ Page, Section, Stylesheet, Styling, _ }

class QLSPageFactory(
    formController: QLSFormController,
    form: Form,
    stylesheet: Stylesheet,
    componentFactory: ComponentFactory)
    extends Callback[Integer, Node]() {

  val defaultStyles: Map[AnswerType, Styling] = Map(
    StringType -> Styling(),
    BooleanType -> Styling(),
    DateType -> Styling(),
    IntegerType -> Styling(),
    DecimalType -> Styling(),
    MoneyType -> Styling()
  )

  override def call(pageNumber: Integer): Node = {
    val page = new VBox()
    val pageToShow = stylesheet.pages(pageNumber)

    makePage(page, pageToShow)
  }

  def makePage(page: VBox, pageToShow: Page): VBox = {
    val defaultPageStyles = mergeStyles(mergeStyles(defaultStyles, stylesheet.defaultStyles), pageToShow.defaultStyles)

    val components = pageToShow.sections.flatMap(section => {
      makeSection(page, section, defaultPageStyles)
    })

    formController.setQuestionControls(components)

    page
  }

  def makeSection(page: VBox, section: Section, defaultStyles: Map[AnswerType, Styling]): Seq[Component[_]] = {
    val defaultSectionStyles = mergeStyles(defaultStyles, section.defaultStyles)

    addSectionHeader(page, section)

    section.statements.flatMap({
      case QuestionStyling(name, style) =>
        form
          .collectQuestionOnName(name)
          .map(question => {
            val styleToUse = defaultSectionStyles(question.answerType) ++ style
            val component = componentFactory.make(QLSQuestion(question, styleToUse))
            question.component = Some(component)
            page.getChildren.add(component)
            component
          })
      case section: Section => makeSection(page, section, defaultStyles)
    })
  }

  def addSectionHeader(questionsOnPage: VBox, section: Section): Boolean = {
    questionsOnPage.getChildren.add(new Label(s"  -- ${section.name} --  "))
  }

  def mergeStyles(left: Map[AnswerType, Styling], right: Map[AnswerType, Styling]): Map[AnswerType, Styling] =
    Semigroup[Map[AnswerType, Styling]].combine(left, right)

}

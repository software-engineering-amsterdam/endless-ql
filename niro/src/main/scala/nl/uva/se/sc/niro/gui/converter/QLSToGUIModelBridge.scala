package nl.uva.se.sc.niro.gui.converter

import nl.uva.se.sc.niro.model.gui.qls
import nl.uva.se.sc.niro.model.gui.qls._
import nl.uva.se.sc.niro.model.qls._
import nl.uva.se.sc.niro.util.StringUtil

/**
  * Converts a AST model into a GUI model. During this conversion the AST model is flattened. In the GUI model the
  * questions are represented as a list. If a question in the AST appears within a (nested) if-construct the
  * visibility property of GUI question will consist of all intermediate expressions logical 'and'ed to ensure the
  * desired behaviour.
  */
object QLSToGUIModelBridge {
  def convertStylesheet(stylesheet: QLStylesheet): GUIStylesheet = {
    val defaultStyles = stylesheet.defaultStyles.mapValues(GUIStyling(_))
    qls.GUIStylesheet(
      StringUtil.addSpaceOnCaseChange(stylesheet.name),
      stylesheet.pages.map(convertPage),
      defaultStyles)
  }

  def convertPage(page: Page): GUIPage = {
    val defaultStyles = page.defaultStyles.mapValues(GUIStyling(_))
    qls.GUIPage(StringUtil.addSpaceOnCaseChange(page.name), page.sections.map(convertSection), defaultStyles)
  }

  def convertSection(section: Section): GUISection = {
    val defaultStyles = section.defaultStyles.mapValues(GUIStyling(_))
    qls.GUISection(section.name, section.questions.map(convertQuestion), defaultStyles)
  }

  def convertQuestion(question: Question): GUIQuestionStyling =
    GUIQuestionStyling(question.name, GUIStyling(question.styling))
}

package nl.uva.se.sc.niro.qls.model

import nl.uva.se.sc.niro.qls.model.ast.{ Page, QLStylesheet, Section, Statement }
import nl.uva.se.sc.niro.qls.model.gui._
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
    GUIStylesheet(StringUtil.addSpaceOnCaseChange(stylesheet.name), stylesheet.pages.map(convertPage), defaultStyles)
  }

  def convertPage(page: Page): GUIPage = {
    val defaultStyles = page.defaultStyles.mapValues(GUIStyling(_))
    GUIPage(StringUtil.addSpaceOnCaseChange(page.name), page.sections.map(convertSection), defaultStyles)
  }

  def convertSection(section: Section): GUISection = {
    val defaultStyles = section.defaultStyles.mapValues(GUIStyling(_))
    GUISection(section.name, section.statements.map(convertStatement), defaultStyles)
  }

  def convertStatement(statement: Statement): GUIStatement = {
    statement match {
      case ast.Question(name, styling) => GUIQuestionStyling(name, GUIStyling(styling))
      case Section(name, statements, defaultStyles) =>
        gui.GUISection(name, statements.map(convertStatement), defaultStyles.mapValues(GUIStyling(_)))
    }
  }

}

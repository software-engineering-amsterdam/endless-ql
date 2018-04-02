package nl.uva.se.sc.niro.qls.model

import nl.uva.se.sc.niro.qls.model.gui._
import nl.uva.se.sc.niro.util.StringUtil

/**
  * Converts a AST model into a GUI model. During this conversion the AST model is flattened. In the GUI model the
  * questions are represented as a list. If a question in the AST appears within a (nested) if-construct the
  * visibility property of GUI question will consist of all intermediate expressions logical 'and'ed to ensure the
  * desired behaviour.
  */
object QLSModelBridge {

  def convertStylesheet(stylesheet: nl.uva.se.sc.niro.qls.model.ast.QLStylesheet): Stylesheet = {
    val defaultStyles = stylesheet.defaultStyles.mapValues(Styling(_))
    Stylesheet(StringUtil.addSpaceOnCaseChange(stylesheet.name), stylesheet.pages.map(convertPage), defaultStyles)
  }

  def convertPage(page: nl.uva.se.sc.niro.qls.model.ast.Page): Page = {
    val defaultStyles = page.defaultStyles.mapValues(Styling(_))
    Page(StringUtil.addSpaceOnCaseChange(page.name), page.sections.map(convertSection), defaultStyles)
  }

  def convertSection(section: nl.uva.se.sc.niro.qls.model.ast.Section): Section = {
    val defaultStyles = section.defaultStyles.mapValues(Styling(_))
    Section(section.name, section.statements.map(convertStatement), defaultStyles)
  }

  def convertStatement(statement: nl.uva.se.sc.niro.qls.model.ast.Statement): Statement = {
    statement match {
      case nl.uva.se.sc.niro.qls.model.ast.Question(name, styling) => QuestionStyling(name, Styling(styling))
      case nl.uva.se.sc.niro.qls.model.ast.Section(name, statements, defaultStyles) =>
        Section(name, statements.map(convertStatement), defaultStyles.mapValues(Styling(_)))
    }
  }

}

package nl.uva.se.sc.niro.ql.model

import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.ql.model.gui.{ ConditionalFactory, Form, Question, QuestionFactory }
import nl.uva.se.sc.niro.util.StringUtil

/**
  * Converts a AST model into a GUI model. During this conversion the AST model is flattened. In the GUI model the
  * questions are represented as a list. If a question in the AST appears within a (nested) if-construct the
  * visibility property of GUI question will consist of all intermediate expressions logical 'and'ed to ensure the
  * desired behaviour.
  */
object QLModelBridge {
  def convertForm(form: nl.uva.se.sc.niro.ql.model.ast.QLForm): Form = {
    Form(StringUtil.addSpaceOnCaseChange(form.formName), convertStatements(BooleanAnswer(true), form.statements))
  }

  def convertStatements(
      visible: Expression,
      statements: Seq[nl.uva.se.sc.niro.ql.model.ast.Statement]): Seq[Question] = {
    statements.flatMap(statement =>
      statement match {
        case question: nl.uva.se.sc.niro.ql.model.ast.Question =>
          Seq(QuestionFactory.makeGUIQuestion(visible, question))
        case conditional: nl.uva.se.sc.niro.ql.model.ast.Conditional =>
          ConditionalFactory.makeGUIConditional(visible, conditional)
        case _ =>
          Seq.empty
    })
  }

}

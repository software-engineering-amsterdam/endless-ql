package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.expressions.Expression
import nl.uva.se.sc.niro.model.expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.util.StringUtil

/**
  * Converts a AST model into a GUI model. During this conversion the AST model is flattend, the AST model
  * can have nested if-constructs. In the GUI model the questions are transformed into a list. If a question
  * in the AST appears within a nested if-construct the visibility property of GUI question will consist of
  * all intermediate expressions logical 'and'ed to ensure the desired behaviour.
  */
object ModelConverter {
  def convert(form: QLForm): GUIForm = {
    new GUIForm(StringUtil.addSpaceOnCaseChange(form.formName), convert(BooleanAnswer(true), form.statements))
  }

  def convert(visible: Expression, statements: Seq[Statement]): Seq[GUIQuestion] = {
    statements.flatMap(statement =>
      statement match {
        case question: Question       => Seq(GUIQuestionFactory.makeGUIQuestion(visible, question))
        case conditional: Conditional => GUIConditionalFactory.makeGUIConditional(visible, conditional)
        case _                        => Seq.empty
    })
  }

}

package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model.Expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model.{ Conditional, QLForm, Question, Statement }
import nl.uva.se.sc.niro.util.StringUtil

object ModelConverter {
  def convert(form: QLForm): GUIModels = {
    new GUIModels(StringUtil.addSpaceOnCaseChange(form.formName), convert(BooleanAnswer(true), form.statements))
  }

  def convert(visible: Expression, statements: Seq[Statement]): Seq[QuestionInfo] = {
    statements.flatMap(statement =>
      statement match {
        case question: Question       => Seq(QuestionInfo(visible, question))
        case conditional: Conditional => convert(conditional.predicate, conditional.thenStatements)
        case _                        => Seq.empty
    })
  }

}

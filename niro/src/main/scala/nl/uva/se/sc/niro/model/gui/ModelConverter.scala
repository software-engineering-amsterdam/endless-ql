package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.expressions.Expression
import nl.uva.se.sc.niro.model.expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model.{ Conditional, QLForm, Question, Statement }
import nl.uva.se.sc.niro.util.StringUtil

object ModelConverter {
  def convert(form: QLForm): GUIModel = {
    new GUIModel(StringUtil.addSpaceOnCaseChange(form.formName), convert(BooleanAnswer(true), form.statements))
  }

  def convert(visible: Expression, statements: Seq[Statement]): Seq[GUIQuestion] = {
    statements.flatMap(statement =>
      statement match {
        case question: Question       => Seq(GUIQuestion(visible, question))
        case conditional: Conditional => convert(conditional.predicate, conditional.thenStatements)
        case _                        => Seq.empty
    })
  }

}

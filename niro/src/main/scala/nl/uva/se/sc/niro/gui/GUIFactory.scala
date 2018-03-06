package nl.uva.se.sc.niro.gui

import javafx.scene.control.Control
import nl.uva.se.sc.niro.model.Expressions.Expression
import nl.uva.se.sc.niro.model.Expressions.answers.BooleanAnswer
import nl.uva.se.sc.niro.model.{ Conditional, QLForm, Question, Statement }

object GUIFactory {
  def makeGUI(form: QLForm): Seq[Control] = {
    makeGUI(BooleanAnswer(true), form.statements)
  }

  def makeGUI(visible: Expression, statements: Seq[Statement]): Seq[Control] = {
    statements.flatMap(statement =>
      statement match {
        case question: Question       => Seq(WidgetFactory.makeWidget(visible, question))
        case conditional: Conditional => makeGUI(conditional.predicate, conditional.thenStatements)
        case _                        => Seq.empty
    })
  }
}

package nl.uva.se.sc.niro.model.gui

import nl.uva.se.sc.niro.model.Expressions.{ BinaryOperation, Expression }
import nl.uva.se.sc.niro.model.{ And, Conditional }

object GUIConditionalFactory {
  def makeGUIConditional(visible: Expression, conditional: Conditional): Seq[GUIQuestion] = {
    ModelConverter.convert(combineConditions(visible, conditional.predicate), conditional.thenStatements)
  }

  private def combineConditions(visible: Expression, predicate: Expression): Expression =
    BinaryOperation(And, visible, predicate)

}

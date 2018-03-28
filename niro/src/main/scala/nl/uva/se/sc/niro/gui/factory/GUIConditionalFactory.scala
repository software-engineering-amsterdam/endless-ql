package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.gui.converter.QLToGUIModelBridge
import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion
import nl.uva.se.sc.niro.model.ql.Conditional
import nl.uva.se.sc.niro.model.ql.expressions.{ And, Expression }

object GUIConditionalFactory {

  def makeGUIConditional(visible: Expression, conditional: Conditional): Seq[GUIQuestion] = {
    QLToGUIModelBridge.convertStatements(combineConditions(visible, conditional.predicate), conditional.thenStatements)
  }

  private def combineConditions(visible: Expression, predicate: Expression): Expression = {
    And(visible, predicate)
  }

}

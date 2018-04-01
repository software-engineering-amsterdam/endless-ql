package nl.uva.se.sc.niro.ql.model.gui

import nl.uva.se.sc.niro.ql.model.QLToGUIModelBridge
import nl.uva.se.sc.niro.ql.model.ast.Conditional
import nl.uva.se.sc.niro.ql.model.ast.expressions.{ And, Expression }

object GUIConditionalFactory {

  def makeGUIConditional(visible: Expression, conditional: Conditional): Seq[GUIQuestion] = {
    QLToGUIModelBridge.convertStatements(combineConditions(visible, conditional.predicate), conditional.thenStatements)
  }

  private def combineConditions(visible: Expression, predicate: Expression): Expression = {
    And(visible, predicate)
  }

}

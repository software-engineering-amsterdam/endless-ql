package nl.uva.se.sc.niro.gui.component

import nl.uva.se.sc.niro.model.gui.ql.GUIQuestion

trait ComponentFactory {
  def make(question: GUIQuestion): Component[_]
}

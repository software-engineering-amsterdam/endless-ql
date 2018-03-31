package nl.uva.se.sc.niro.ql.view.component

import nl.uva.se.sc.niro.ql.model.gui.GUIQuestion

trait ComponentFactory {
  def make(question: GUIQuestion): Component[_]
}

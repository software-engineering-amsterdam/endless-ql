package nl.uva.se.sc.niro.gui.control

import nl.uva.se.sc.niro.model.gui.GUIQuestion

trait ComponentFactory {
  def make(question: GUIQuestion): Component[_]
}

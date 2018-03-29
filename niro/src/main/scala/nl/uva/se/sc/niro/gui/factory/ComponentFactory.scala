package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.gui.control._
import nl.uva.se.sc.niro.model.gui.GUIQuestion

trait ComponentFactory {
  def make(question: GUIQuestion): Component[_]
}

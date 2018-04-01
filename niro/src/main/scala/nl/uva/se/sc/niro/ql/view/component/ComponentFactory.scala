package nl.uva.se.sc.niro.ql.view.component

import nl.uva.se.sc.niro.ql.model.gui.Question

trait ComponentFactory {
  def make(question: Question): Component[_]
}

package nl.uva.se.sc.niro.gui.listener

import nl.uva.se.sc.niro.gui.control.ql.QLWidget

trait ValueChangedListener {
  def valueChanged(control: QLWidget[_]): Unit
}

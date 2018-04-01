package nl.uva.se.sc.niro.ql.view

import nl.uva.se.sc.niro.ql.view.widget.Widget

trait ValueChangedListener {
  def valueChanged(control: Widget[_]): Unit
}

package nl.uva.se.sc.niro.ql.view

import nl.uva.se.sc.niro.ql.view.widget.QLWidget

trait ValueChangedListener {
  def valueChanged(control: QLWidget[_]): Unit
}

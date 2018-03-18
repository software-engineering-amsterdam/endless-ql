package nl.uva.se.sc.niro.gui.converter

import nl.uva.se.sc.niro.model.gui.{ GUIForm, QLSGUIQuestion }
import nl.uva.se.sc.niro.model.qls.QLStylesheet

object StyleDecorator {
  def applyStyle(form: GUIForm, stylesheet: QLStylesheet): GUIForm =
    GUIForm(form.name, form.questions.map(QLSGUIQuestion(_)))
}

package nl.uva.se.sc.niro.gui.converter

import nl.uva.se.sc.niro.model.gui._
import nl.uva.se.sc.niro.model.qls.QLStylesheet

object StyleDecorator {
  def applyStyle(form: GUIForm, stylesheet: QLStylesheet): GUIForm = {
    GUIForm(form.name, form.questions.map(applyStyle(_, stylesheet)))
  }

  def applyStyle(question: GUIQuestion, stylesheet: QLStylesheet): QLSGUIQuestion = {
    question match {
      case q: QLGUIQuestion  =>
        val styles = stylesheet.collectStylesForQuesiotn(q.id)
        // FIXME
        QLSGUIQuestion(question, Radio("Ja", "Nee"))
      case q: QLSGUIQuestion => q
    }
  }
}

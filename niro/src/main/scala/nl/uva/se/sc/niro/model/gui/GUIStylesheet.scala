package nl.uva.se.sc.niro.model.gui

/**
  * Model used by the frontend
  */
import nl.uva.se.sc.niro.model.ql.AnswerType
import nl.uva.se.sc.niro.model.qls.{ ComboBox, Radio, SpinBox, WidgetType }

case class GUIStylesheet(name: String, pages: Seq[GUIPage], defaultStyles: Map[AnswerType, GUIStyle])

case class GUIPage(name: String, sections: Seq[GUISection], defaultStyles: Map[AnswerType, GUIStyle])

case class GUISection(name: String, questions: Seq[GUIStyledQuestion], defaultStyles: Map[AnswerType, GUIStyle])

case class GUIStyledQuestion(name: String, style: Option[GUIStyle])

abstract class GUIStyle

case class GUIDefaultStyle() extends GUIStyle
case class GUISpinBoxStyle() extends GUIStyle
case class GUIComboBoxStyle(trueLabel: String, falseLabel: String) extends GUIStyle
case class GUIRadioStyle(trueLabel: String, falseLabel: String) extends GUIStyle

object GUIStyle {

  def apply(widgetType: Option[WidgetType]): GUIStyle = widgetType.map(GUIStyle(_)).getOrElse(GUIDefaultStyle())

  def apply(widgetType: WidgetType): GUIStyle = widgetType match {
    case SpinBox()                       => GUISpinBoxStyle()
    case ComboBox(trueValue, falseValue) => GUIComboBoxStyle(trueValue, falseValue)
    case Radio(trueValue, falseValue)    => GUIRadioStyle(trueValue, falseValue)
  }
}

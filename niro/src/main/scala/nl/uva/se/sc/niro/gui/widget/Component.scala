package nl.uva.se.sc.niro.gui.widget

import javafx.scene.control._
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.gui.factory.WidgetFactory
import nl.uva.se.sc.niro.model.expressions.answers._
import nl.uva.se.sc.niro.model.gui.GUIQuestion

import scala.collection.mutable

class Component(id: String, label: Label, control: Control) extends HBox {
  getChildren.addAll(label, control)
  managedProperty().bind(visibleProperty())

  label.setPrefWidth(200)
  control.setPrefWidth(200)

  def setReadOnly(value: Boolean): Unit = control.setDisable(value)

  def update(dictionary: mutable.Map[String, Answer]): Unit = {
      val value = dictionary(id)
      value match {
        case b: BooleanAnswer => control.asInstanceOf[CheckBox].setSelected(b.possibleValue.getOrElse(false))
        case d: DateAnswer    => control.asInstanceOf[DatePicker].setValue(d.possibleValue.orNull)
        case s: StringAnswer  => control.asInstanceOf[TextField].setText(s.possibleValue.getOrElse(""))
        case i: IntAnswer     => control.asInstanceOf[TextField].getTextFormatter.asInstanceOf[TextFormatter[Int]].setValue(intOrNull(i.possibleValue))
        case d: DecAnswer     => control.asInstanceOf[TextField].getTextFormatter.asInstanceOf[TextFormatter[java.math.BigDecimal]].setValue(decOrNull(d.possibleValue))
        case m: MoneyAnswer   => control.asInstanceOf[TextField].getTextFormatter.asInstanceOf[TextFormatter[java.math.BigDecimal]].setValue(decOrNull(m.possibleValue))
      }
    }

  private def intOrNull(int: Option[Int]): java.lang.Integer = if (int.isDefined) int.get else null
  private def decOrNull(int: Option[BigDecimal]): java.math.BigDecimal = if (int.isDefined) int.get.bigDecimal else null
}

object ComponentFactory {
  def make(question: GUIQuestion): Component = {
    val component = new Component(question.id, new Label(question.label), WidgetFactory.make(question))
    component.setReadOnly(question.isReadOnly)
    component
  }


}

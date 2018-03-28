package nl.uva.se.sc.niro.gui.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, BooleanAnswer }

import scala.collection.mutable

case class BooleanComponent(id: String, label: Label, control: QLWidget[Boolean])
  extends Component[Boolean](id, label, control) {
  override def getValue: Option[BooleanAnswer] = Option(control.value).map(BooleanAnswer(_))
  override def setValue(value: Option[Boolean]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(convert))
  private def fromOption(value: Option[Boolean]): Boolean = value.getOrElse(false)
  def convert(answer: Answer): Option[Boolean] = answer match {
    case BooleanAnswer(value) => value
  }
}

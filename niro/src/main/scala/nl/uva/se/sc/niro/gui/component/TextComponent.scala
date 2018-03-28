package nl.uva.se.sc.niro.gui.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, StringAnswer }

import scala.collection.mutable

case class TextComponent(id: String, label: Label, control: QLWidget[String])
  extends Component[String](id, label, control) {
  override def getValue: Option[StringAnswer] = Option(control.value).map(StringAnswer(_))
  override def setValue(value: Option[String]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.map(_.toString).filter(_.nonEmpty)))
  private def fromOption(value: Option[String]): String = value.orNull
}

package nl.uva.se.sc.niro.ql.view.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ Answer, StringAnswer }
import nl.uva.se.sc.niro.ql.view.widget.Widget

import scala.collection.mutable

case class TextComponent(id: String, label: Label, control: Widget[String])
    extends Component[String](id, label, control) {

  override def getValue: Option[StringAnswer] = Option(control.value).map(StringAnswer)

  override def setValue(value: Option[String]): Unit = control.value(fromOption(value))

  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(answer => Option(answer).map(_.value.toString).filter(_.nonEmpty)))

  private def fromOption(value: Option[String]): String = value.orNull
}

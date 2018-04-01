package nl.uva.se.sc.niro.ql.view.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ Answer, BooleanAnswer }
import nl.uva.se.sc.niro.ql.view.widget.Widget

import scala.collection.mutable

case class BooleanComponent(id: String, label: Label, control: Widget[java.lang.Boolean])
    extends Component[Boolean](id, label, control) {

  override def getValue: Option[BooleanAnswer] = Option(control.value).map(BooleanAnswer(_))

  override def setValue(value: Option[Boolean]): Unit = control.value(fromOption(value))

  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).map(convert))

  private def fromOption(value: Option[Boolean]): Boolean = value.getOrElse(false)

  def convert(answer: Answer): Boolean = answer match {
    case BooleanAnswer(value) => value
  }
}

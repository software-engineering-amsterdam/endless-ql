package nl.uva.se.sc.niro.gui.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.widget.ql.QLWidget
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, MoneyAnswer }

import scala.collection.mutable

case class MoneyComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
  extends Component[BigDecimal](id, label, control) {
  override def getValue: Option[MoneyAnswer] = Option(control.value).map(MoneyAnswer(_))
  override def setValue(value: Option[BigDecimal]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(convert))
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal = value.map(_.bigDecimal).orNull
  def convert(answer: Answer): Option[BigDecimal] = answer match {
    case MoneyAnswer(value) => value
  }
}

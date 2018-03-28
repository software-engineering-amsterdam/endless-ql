package nl.uva.se.sc.niro.gui.component

import java.time.LocalDate

import javafx.scene.control.Label
import nl.uva.se.sc.niro.gui.control.ql.QLWidget
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DateAnswer }

import scala.collection.mutable

case class DateComponent(id: String, label: Label, control: QLWidget[LocalDate])
  extends Component[LocalDate](id, label, control) {
  override def getValue: Option[DateAnswer] = Option(control.value).map(DateAnswer(_))
  override def setValue(value: Option[LocalDate]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(convert))
  private def fromOption(value: Option[LocalDate]): LocalDate = value.orNull
  def convert(answer: Answer): Option[LocalDate] = answer match {
    case DateAnswer(value) => value
  }
}

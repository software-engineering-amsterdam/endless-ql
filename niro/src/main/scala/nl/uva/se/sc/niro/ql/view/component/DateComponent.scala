package nl.uva.se.sc.niro.ql.view.component

import java.time.LocalDate

import javafx.scene.control.Label
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ Answer, DateAnswer }
import nl.uva.se.sc.niro.ql.view.widget.Widget

import scala.collection.mutable

case class DateComponent(id: String, label: Label, control: Widget[LocalDate])
    extends Component[LocalDate](id, label, control) {

  override def getValue: Option[DateAnswer] = Option(control.value).map(DateAnswer(_))

  override def setValue(value: Option[LocalDate]): Unit = control.value(fromOption(value))

  override def updateValue(valueStore: mutable.Map[String, Answer]): Unit =
    setValue(valueStore.get(id).map(convert))

  private def fromOption(value: Option[LocalDate]): LocalDate = value.orNull

  def convert(answer: Answer): LocalDate = answer match {
    case DateAnswer(value) => value
  }
}

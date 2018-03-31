package nl.uva.se.sc.niro.ql.view.component

import javafx.scene.control.Label
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.{ Answer, IntegerAnswer }
import nl.uva.se.sc.niro.ql.view.widget.QLWidget

import scala.collection.mutable

case class IntegerComponent(id: String, label: Label, control: QLWidget[java.math.BigInteger])
    extends Component[BigInt](id, label, control) {
  override def getValue: Option[IntegerAnswer] = Option(control.value).map(IntegerAnswer(_))
  override def setValue(value: Option[BigInt]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).map(convert))
  private def fromOption(value: Option[BigInt]): java.math.BigInteger = value.map(_.bigInteger).orNull
  def convert(answer: Answer): BigInt = answer match {
    case IntegerAnswer(value) => value
  }
}

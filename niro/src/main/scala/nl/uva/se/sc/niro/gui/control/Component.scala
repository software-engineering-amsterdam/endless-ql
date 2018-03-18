package nl.uva.se.sc.niro.gui.control

import java.time.LocalDate

import javafx.scene.control._
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.gui.listener.{ ComponentChangedListener, ValueChangedListener }
import nl.uva.se.sc.niro.model.ql.expressions.answers._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

abstract class Component[T](id: String, label: Label, control: QLWidget[_]) extends HBox with ValueChangedListener {
  private val componentChangedListeners = ArrayBuffer[ComponentChangedListener]()

  control.addValueChangedListener(this)
  getChildren.addAll(label, control)
  managedProperty().bind(visibleProperty())

  label.setPrefWidth(200)
  control.setPrefWidth(200)

  def getQuestionId: String = id

  def getControl: QLWidget[_] = control

  def setReadOnly(value: Boolean): Unit = control.setDisable(value)
  def isReadOnly: Boolean = control.isDisabled

  def updateValue(dictionary: mutable.Map[String, Answer]): Unit
  def getValue: Answer
  def setValue(value: Option[T]): Unit

  def addComponentChangedListener(componentChangedListener: ComponentChangedListener): Unit =
    componentChangedListeners.append(componentChangedListener)

  override def valueChanged(control: QLWidget[_]): Unit = {
    componentChangedListeners.foreach(_.componentChanged(this))
  }

}

case class StringComponent(id: String, label: Label, control: QLWidget[String])
    extends Component[String](id, label, control) {
  override def getValue: StringAnswer = StringAnswer(control.getValue)
  override def setValue(value: Option[String]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.map(_.toString)))
  private def fromOption(value: Option[String]): String = value.orNull
}

case class BooleanComponent(id: String, label: Label, control: QLWidget[Boolean])
    extends Component[Boolean](id, label, control) {
  override def getValue: BooleanAnswer = BooleanAnswer(control.getValue)
  override def setValue(value: Option[Boolean]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.asInstanceOf[Option[Boolean]]))
  private def fromOption(value: Option[Boolean]): Boolean = value.getOrElse(false)
}

case class DateComponent(id: String, label: Label, control: QLWidget[LocalDate])
    extends Component[LocalDate](id, label, control) {
  override def getValue: DateAnswer = DateAnswer(control.getValue)
  override def setValue(value: Option[LocalDate]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.asInstanceOf[Option[LocalDate]]))
  private def fromOption(value: Option[LocalDate]): LocalDate = value.orNull
}

case class IntegerComponent(id: String, label: Label, control: QLWidget[Integer])
    extends Component[Int](id, label, control) {
  override def getValue: IntegerAnswer = IntegerAnswer(control.getValue)
  override def setValue(value: Option[Int]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.asInstanceOf[Option[Int]]))
  private def fromOption(value: Option[Int]): java.lang.Integer = value.map(new Integer(_)).orNull
}

case class DecimalComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {
  override def getValue: DecimalAnswer = DecimalAnswer(control.getValue)
  override def setValue(value: Option[BigDecimal]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.asInstanceOf[Option[BigDecimal]]))
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal = value.map(_.bigDecimal).orNull
}

case class MoneyComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {
  override def getValue: MoneyAnswer = MoneyAnswer(control.getValue)
  override def setValue(value: Option[BigDecimal]): Unit = control.setValue(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.asInstanceOf[Option[BigDecimal]]))
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal = value.map(_.bigDecimal).orNull
}

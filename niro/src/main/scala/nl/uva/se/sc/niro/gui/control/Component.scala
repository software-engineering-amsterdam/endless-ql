package nl.uva.se.sc.niro.gui.control

import java.math.BigInteger
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
  def getValue: Option[Answer]
  def setValue(value: Option[T]): Unit

  def addComponentChangedListener(componentChangedListener: ComponentChangedListener): Unit =
    componentChangedListeners.append(componentChangedListener)

  override def valueChanged(control: QLWidget[_]): Unit = {
    componentChangedListeners.foreach(_.componentChanged(this))
  }

}

case class StringComponent(id: String, label: Label, control: QLWidget[String])
    extends Component[String](id, label, control) {
  override def getValue: Option[StringAnswer] = Option(control.value).map(StringAnswer(_))
  override def setValue(value: Option[String]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(_.possibleValue.map(_.toString).filter(_.nonEmpty)))
  private def fromOption(value: Option[String]): String = value.orNull
}

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

case class IntegerComponent(id: String, label: Label, control: QLWidget[java.math.BigInteger])
    extends Component[BigInt](id, label, control) {
  override def getValue: Option[IntegerAnswer] = Option(control.value).map(IntegerAnswer(_))
  override def setValue(value: Option[BigInt]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(convert))
  private def fromOption(value: Option[BigInt]): java.math.BigInteger = value.map(_.bigInteger).orNull
  def convert(answer: Answer): Option[BigInt] = answer match {
    case IntegerAnswer(value) => value
  }
}

case class DecimalComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {
  override def getValue: Option[DecimalAnswer] = Option(control.value).map(DecimalAnswer(_))
  override def setValue(value: Option[BigDecimal]): Unit = control.value(fromOption(value))
  override def updateValue(dictionary: mutable.Map[String, Answer]): Unit =
    setValue(dictionary.get(id).flatMap(convert))
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal = value.map(_.bigDecimal).orNull
  def convert(answer: Answer): Option[BigDecimal] = answer match {
    case DecimalAnswer(value) => value
  }
}

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

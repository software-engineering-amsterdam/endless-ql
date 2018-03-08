package nl.uva.se.sc.niro.gui.widget

import java.time.LocalDate

import javafx.scene.control._
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.gui.control.QLWidget
import nl.uva.se.sc.niro.gui.factory._
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.model.expressions.answers._
import nl.uva.se.sc.niro.model.gui.GUIQuestion

import scala.collection.mutable

abstract class Component[T](id: String, label: Label, control: QLWidget[_]) extends HBox {
  getChildren.addAll(label, control)
  managedProperty().bind(visibleProperty())

  label.setPrefWidth(200)
  control.setPrefWidth(200)

  def setReadOnly(value: Boolean): Unit = control.setDisable(value)
  def isReadOnly: Boolean = control.isDisabled

  def update(dictionary: mutable.Map[String, Answer]): Unit
  def getValue: Option[T]
  def setValue(value: Option[T]): Unit
}

case class StringComponent(id: String, label: Label, control: QLWidget[String]) extends Component[String](id, label, control) {
  override def getValue: Option[String] = toOption(control.getValue)
  override def setValue(value: Option[String]): Unit = control.setValue(fromOption(value))
  override def update(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(dictionary(id).possibleValue.map(_.toString))
  }
  private def fromOption(value: Option[String]): String = value.orNull
  private def toOption(value: String): Option[String] = Option(value)
}

case class BooleanComponent(id: String, label: Label, control: QLWidget[Boolean]) extends Component[Boolean](id, label, control) {
  override def getValue: Option[Boolean] = toOption(control.getValue)
  override def setValue(value: Option[Boolean]): Unit = control.setValue(fromOption(value))
  override def update(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(dictionary(id).possibleValue.asInstanceOf[Option[Boolean]])
  }
  private def fromOption(value: Option[Boolean]): Boolean = value.getOrElse(false)
  private def toOption(value: Boolean): Option[Boolean] = Option(value)
}

case class DateComponent(id: String, label: Label, control: QLWidget[LocalDate]) extends Component[LocalDate](id, label, control) {
  override def getValue: Option[LocalDate] = toOption(control.getValue)
  override def setValue(value: Option[LocalDate]): Unit = control.setValue(fromOption(value))
  override def update(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(dictionary(id).possibleValue.asInstanceOf[Option[LocalDate]])
  }
  private def fromOption(value: Option[LocalDate]): LocalDate = if (value.isDefined) value.get else null
  private def toOption(value: LocalDate): Option[LocalDate] = Option(value)
}

case class IntegerComponent(id: String, label: Label, control: QLWidget[Integer]) extends Component[Int](id, label, control) {
  override def getValue: Option[Int] = toOption(control.getValue)
  override def setValue(value: Option[Int]): Unit = control.setValue(fromOption(value))
  override def update(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(dictionary(id).possibleValue.asInstanceOf[Option[Int]])
  }
  private def fromOption(value: Option[Int]): java.lang.Integer = if (value.isDefined) value.get else null
  private def toOption(value: Int): Option[Int] = Option(value)
}

case class DecimalComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {
  override def getValue: Option[BigDecimal] = toOption(control.getValue)
  override def setValue(value: Option[BigDecimal]): Unit = control.setValue(fromOption(value))
  override def update(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(dictionary(id).possibleValue.asInstanceOf[Option[BigDecimal]])
  }
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal =
    if (value.isDefined) value.get.bigDecimal else null
  private def toOption(value: BigDecimal): Option[BigDecimal] = Option(value)
}

case class MoneyComponent(id: String, label: Label, control: QLWidget[java.math.BigDecimal])
    extends Component[BigDecimal](id, label, control) {
  override def getValue: Option[BigDecimal] = toOption(control.getValue)
  override def setValue(value: Option[BigDecimal]): Unit = control.setValue(fromOption(value))
  override def update(dictionary: mutable.Map[String, Answer]): Unit = {
    setValue(dictionary(id).possibleValue.asInstanceOf[Option[BigDecimal]])
  }
  private def fromOption(value: Option[BigDecimal]): java.math.BigDecimal =
    if (value.isDefined) value.get.bigDecimal else null
  private def toOption(value: java.math.BigDecimal): Option[BigDecimal] = Option(value)
}

object ComponentFactory {
  def make(question: GUIQuestion): Component[_] = {
    val component = question.answerType match {
      case StringType  => StringComponent(question.id, new Label(question.label), StringWidgetFactory.make(question))
      case BooleanType => BooleanComponent(question.id, new Label(question.label), BooleanWidgetFactory.make(question))
      case DateType    => DateComponent(question.id, new Label(question.label), DateWidgetFactory.make(question))
      case IntegerType => IntegerComponent(question.id, new Label(question.label), IntegerWidgetFactory.make(question))
      case DecimalType => DecimalComponent(question.id, new Label(question.label), DecimalWidgetFactory.make(question))
      case MoneyType   => MoneyComponent(question.id, new Label(question.label), MoneyWidgetFactory.make(question))
    }
    component.setReadOnly(question.isReadOnly)
    question.component = Some(component)
    component
  }

}

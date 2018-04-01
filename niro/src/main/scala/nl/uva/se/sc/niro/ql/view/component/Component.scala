package nl.uva.se.sc.niro.ql.view.component

import javafx.scene.control._
import javafx.scene.layout.HBox
import nl.uva.se.sc.niro.ql.model.ast.expressions.answers.Answer
import nl.uva.se.sc.niro.ql.view.widget.Widget
import nl.uva.se.sc.niro.ql.view.{ ComponentChangedListener, ValueChangedListener }

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

abstract class Component[T](id: String, label: Label, control: Widget[_]) extends HBox with ValueChangedListener {
  private val componentChangedListeners = ArrayBuffer[ComponentChangedListener]()

  control.addValueChangedListener(this)
  getChildren.addAll(label, control)
  managedProperty().bind(visibleProperty())

  label.setPrefWidth(200)
  control.setPrefWidth(200)

  def getQuestionId: String = id

  def setReadOnly(value: Boolean): Unit = control.setDisable(value)
  def isReadOnly: Boolean = control.isDisabled

  def updateValue(dictionary: mutable.Map[String, Answer]): Unit

  def getValue: Option[Answer]

  def setValue(value: Option[T]): Unit

  def addComponentChangedListener(componentChangedListener: ComponentChangedListener): Unit =
    componentChangedListeners.append(componentChangedListener)

  override def valueChanged(control: Widget[_]): Unit = {
    componentChangedListeners.foreach(_.componentChanged(this))
  }

}

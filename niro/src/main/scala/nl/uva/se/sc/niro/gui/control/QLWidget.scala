package nl.uva.se.sc.niro.gui.control

import javafx.scene.Node
import nl.uva.se.sc.niro.gui.listener.ValueChangedListener

import scala.collection.mutable.ArrayBuffer

trait QLWidget[T] extends Node {
  private val valueChangedListeners = ArrayBuffer[ValueChangedListener]()
  def value(value: T): Unit
  def value: T
  def setPrefWidth(width: Double): Unit
  def addValueChangedListener(valueChangedListener: ValueChangedListener): Unit =
    valueChangedListeners.append(valueChangedListener)
  protected def valueChanged(): Unit =
    valueChangedListeners.foreach(_.valueChanged(this))
}

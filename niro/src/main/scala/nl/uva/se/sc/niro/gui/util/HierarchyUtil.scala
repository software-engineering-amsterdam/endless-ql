package nl.uva.se.sc.niro.gui.util

object HierarchyUtil {
  def downcast[P, T <: P](node: P): T = node.asInstanceOf[T]
}
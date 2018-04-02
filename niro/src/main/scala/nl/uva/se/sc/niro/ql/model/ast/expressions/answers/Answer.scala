package nl.uva.se.sc.niro.ql.model.ast.expressions.answers

import nl.uva.se.sc.niro.ql.model.ast.expressions.Expression

trait Answer extends Expression {

  type T

  val value: T

  def isTrue: Boolean = false

  def plus(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def subtract(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def multiply(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def divide(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def minus: Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")

  def lessThan(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def lessThanEquals(right: Answer): Answer =
    throw new UnsupportedOperationException(s"Operation not supported on $this")
  def greaterThenEquals(right: Answer): Answer =
    throw new UnsupportedOperationException(s"Operation not supported on $this")
  def greaterThen(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def notEquals(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def equals(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")

  def and(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def or(right: Answer): Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")
  def negate: Answer = throw new UnsupportedOperationException(s"Operation not supported on $this")

}

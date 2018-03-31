package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.expressions.Expression

abstract class Answer extends Expression {

  type T

  val possibleValue: Option[T]

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

  def combine[R](that: Answer)(f: (T, that.T) => R): Option[R] =
    for {
      thisValue <- possibleValue
      thatValue <- that.possibleValue
    } yield f(thisValue, thatValue)
}

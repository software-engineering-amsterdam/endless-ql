package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.Expression.Answer

trait Arithmetics[T] extends Answer {

  def add(other: T): T

  def sub(other: T): T

  def mul(other: T): T

  def div(other: T): T
}
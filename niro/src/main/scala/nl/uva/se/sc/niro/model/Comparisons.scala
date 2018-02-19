package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.Expressions.answers.BooleanAnswer

trait Comparisons[T] {

  def lt(other: T): BooleanAnswer

  def lTe(other: T): BooleanAnswer

  def gTe(other: T): BooleanAnswer

  def gt(other: T): BooleanAnswer

  def ne(other: T): BooleanAnswer

  def eq(other: T): BooleanAnswer
}
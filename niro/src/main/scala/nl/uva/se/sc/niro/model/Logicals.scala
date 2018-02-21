package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.Expressions.answers.BooleanAnswer

trait Logicals[T] {

  def or(other: T): BooleanAnswer

  def and(other: T): BooleanAnswer
}
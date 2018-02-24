package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.BooleanAnswer

trait Logicals[SubType<: Answer] {
  def and(x: SubType, y: SubType): BooleanAnswer
  def or(x: SubType, y: SubType): BooleanAnswer
}

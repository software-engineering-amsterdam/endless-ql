package nl.uva.se.sc.niro.model.Expressions

trait BasicArithmetics[SubType<:Answer] {
  def plus(x: SubType, y: SubType): SubType
  def minus(x: SubType, y: SubType): SubType
  def times(x: SubType, y: SubType): SubType
  def div(x: SubType, y: SubType): SubType
  def negate(x: SubType): SubType
}
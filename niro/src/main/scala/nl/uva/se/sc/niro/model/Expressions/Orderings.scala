package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer }

object Orderings {
  trait Orderings[SubType<:Answer] {
    def lt(x: SubType, y: SubType): BooleanAnswer
    def lte(x: SubType, y: SubType): BooleanAnswer
    def gte(x: SubType, y: SubType): BooleanAnswer
    def gt(x: SubType, y: SubType): BooleanAnswer
    def ne(x: SubType, y: SubType): BooleanAnswer
    def eq(x: SubType, y: SubType): BooleanAnswer

    trait IntAnswerCanDoOrderings extends Orderings[IntAnswer] {
      import IntAnswerCanDoOrderings._
      def lt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ < _))
      def lte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ <= _))
      def gte(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ >= _))
      def gt(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ > _))
      def ne(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ != _))
      def eq(x: IntAnswer, y: IntAnswer): BooleanAnswer = BooleanAnswer(combine(x, y)(_ == _))
    }
    object IntAnswerCanDoOrderings extends IntAnswerCanDoOrderings {
      private def combine(x: IntAnswer, y: IntAnswer)(f: (Int, Int) => Boolean): Option[Boolean] = for {
        thisValue <- x.possibleValue
        thatValue <- y.possibleValue
      } yield f(thisValue, thatValue)
    }
  }
}

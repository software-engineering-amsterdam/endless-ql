package nl.uva.se.sc.niro.model.expressions

import java.time.LocalDate

import nl.uva.se.sc.niro.model.expressions.Orderings.DateAnswerCanDoOrderings._
import nl.uva.se.sc.niro.model.expressions.answers.{ BooleanAnswer, DateAnswer }
import org.scalatest.WordSpec

class OrderingsTest extends WordSpec {
  "Orderings type class" should {
    "order dateAnswer" can {
      "Lt positive" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) < DateAnswer(LocalDate.parse("2018-03-09"))) == BooleanAnswer(
            true))
      }
      "Lte positive equal" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) <= DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            true))
      }
      "Gte positive equal" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) >= DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            true))
      }
      "Lte positive less" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-07")) <= DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            true))
      }
      "Gte positive greate" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-09")) >= DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            true))
      }
      "Gt positive" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-09")) > DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            true))
      }
      "Ne positive" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-09")) !== DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            true))
      }
      "Eq positive" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) === DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            true))
      }
      "Lt negative" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) < DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            false))
      }
      "Lte negative" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-09")) <= DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            false))
      }
      "Gte negative" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) >= DateAnswer(LocalDate.parse("2018-03-09"))) == BooleanAnswer(
            false))
      }
      "Gt negative" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) > DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            false))
      }
      "Ne negative" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-08")) !== DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            false))
      }
      "Eq negative" in {
        assert(
          (DateAnswer(LocalDate.parse("2018-03-09")) === DateAnswer(LocalDate.parse("2018-03-08"))) == BooleanAnswer(
            false))
      }
    }
  }
}

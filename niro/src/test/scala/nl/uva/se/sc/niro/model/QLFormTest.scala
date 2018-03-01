package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer }
import nl.uva.se.sc.niro.model.Expressions.{ BinaryOperation, Reference }
import org.scalatest.WordSpec

class QLFormTest extends WordSpec {

  "QLFormTest" should {

    "save" in {
      val qLForm = QLForm(
        formName = "Revenue",
        statements = List(
          Question("revenue", "How much did you earn", IntegerType, IntAnswer()),
          Question("expenses", "How much did you earn", IntegerType, IntAnswer()),
          Conditional(
            BooleanAnswer(true),
            Seq(
              Question(
                "profit",
                "You still have",
                IntegerType,
                BinaryOperation(Sub, Reference("revenue"), Reference("expenses")))
            ))
        )
      )

      val result = qLForm.save("revenue", IntAnswer(5))

      val expected = QLForm(
        "Revenue",
        List(
          Question("revenue", "How much did you earn", IntegerType, IntAnswer(Some(5)), None),
          Question("expenses", "How much did you earn", IntegerType, IntAnswer(None), None),
          Conditional(
            BooleanAnswer(Some(true)),
            List(
              Question(
                "profit",
                "You still have",
                IntegerType,
                BinaryOperation(Sub, Reference("revenue"), Reference("expenses")),
                None
              )
            )
          )
        )
      )

      assert(result == expected)
    }

  }
}

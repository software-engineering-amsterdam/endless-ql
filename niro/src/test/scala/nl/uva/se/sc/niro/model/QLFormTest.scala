package nl.uva.se.sc.niro.model

import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.model.expressions.answers.{ BooleanAnswer, IntAnswer }
import nl.uva.se.sc.niro.model.expressions.{ BinaryOperation, Reference }
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
            ),
            None
          )
        )
      )

      assert(result == expected)
    }

  }

  "Conditional must turn true" should {

    "save" in {
      val qLForm = QLForm(
        formName = "ConditionMustTurnTrue",
        statements = List(
          Question("yesOrNo", "Yes or No", BooleanType, BooleanAnswer()),
          Conditional(
            predicate = Reference("yesOrNo"),
            Seq(
              Question("happy", "Are you happy", BooleanType, BooleanAnswer())
            ))
        )
      )

      val result = Evaluator.evaluate(qLForm.save("yesOrNo", BooleanAnswer(true)))

      val expected = QLForm(
        formName = "ConditionMustTurnTrue",
        statements = List(
          Question("yesOrNo", "Yes or No", BooleanType, BooleanAnswer(true), Some(BooleanAnswer(true))),
          Conditional(
            predicate = Reference("yesOrNo"),
            Seq(
              Question("happy", "Are you happy", BooleanType, BooleanAnswer(), Some(BooleanAnswer()))
            ),
            answer = Some(BooleanAnswer(true))
          )
        )
      )

      assert(result == expected)
    }

  }
}

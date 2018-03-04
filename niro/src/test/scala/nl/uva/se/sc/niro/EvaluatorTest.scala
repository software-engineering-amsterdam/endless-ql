package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer }
import nl.uva.se.sc.niro.model.Expressions.{ BinaryOperation, Reference }
import nl.uva.se.sc.niro.model._
import org.scalatest.WordSpec

class EvaluatorTest extends WordSpec {

  "EvaluatorTest" should {
    // TODO implement test for the evaluator
    "evaluateConditional" in {}

    "evaluateQuestion" in {}

    "evaluateStatement" in {}

    "evaluateQLForm" in {
      val qLForm = QLForm(
        formName = "Revenue",
        statements = List(
          Question("revenue", "How much did you earn", IntegerType, IntAnswer(1000)),
          Question("expenses", "How much did you earn", IntegerType, IntAnswer(800)),
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

      val result = Evaluator.evaluate(qLForm)
      val expected = QLForm(
        "Revenue",
        List(
          Question(
            "revenue",
            "How much did you earn",
            IntegerType,
            IntAnswer(Some(1000)),
            Some(IntAnswer(Some(1000)))
          ),
          Question(
            "expenses",
            "How much did you earn",
            IntegerType,
            IntAnswer(Some(800)),
            Some(IntAnswer(Some(800)))
          ),
          Conditional(
            BooleanAnswer(Some(true)),
            List(
              Question(
                "profit",
                "You still have",
                IntegerType,
                BinaryOperation(Sub, Reference("revenue"), Reference("expenses")),
                Some(IntAnswer(Some(200)))
              )
            ),
            Some(BooleanAnswer(Some(true)))
          )
        )
      )

      assert(result == expected)
    }
  }
}

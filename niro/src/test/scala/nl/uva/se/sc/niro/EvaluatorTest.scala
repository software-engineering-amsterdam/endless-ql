package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.expressions.answers.{ BooleanAnswer, IntAnswer }
import nl.uva.se.sc.niro.model.expressions.{ BinaryOperation, Reference }
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

      val result = Evaluator.evaluate(qLForm, Map.empty)
      val expected =
        Map("revenue" -> IntAnswer(Some(1000)), "expenses" -> IntAnswer(Some(800)), "profit" -> IntAnswer(Some(200)))

      assert(result == expected)
    }
  }
}

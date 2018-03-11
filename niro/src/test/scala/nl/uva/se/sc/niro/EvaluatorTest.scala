package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.Evaluator.Dictionary
import nl.uva.se.sc.niro.model._
import nl.uva.se.sc.niro.model.expressions.answers.{ BooleanAnswer, DecAnswer, IntAnswer }
import nl.uva.se.sc.niro.model.expressions.{ BinaryOperation, Reference, UnaryOperation }
import org.scalatest.WordSpec

class EvaluatorTest extends WordSpec {

  "EvaluatorTest" should {
    // TODO implement test for the evaluator
    "evaluateConditional" in {}

    "evaluateQuestion" in {}

    "evaluateStatement" in {}

    "evaluateQLForm with single expression" in {
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

    "evaluateQLForm with multiple expressions" in {
      val qlForm = QLForm(
        "EditOrNotToEdit",
        List(
          Question("booleanVariable", "Boolean variable", BooleanType, BooleanAnswer(None)),
          Question("integerVariable", "Integer variable", IntegerType, IntAnswer(None)),
          Question("decimalVariable", "Decimal variable", DecimalType, DecAnswer(None)),
          Conditional(
            Reference("booleanVariable"),
            List(
              Question(
                "integerConstant",
                "Integer constant",
                IntegerType,
                BinaryOperation(Mul, IntAnswer(21), IntAnswer(2))
              ),
              Question("decimalConstant", "Decimal constant", DecimalType, DecAnswer(42.4))
            )
          ),
          Conditional(
            UnaryOperation(Neg, Reference("booleanVariable")),
            List(
              Question(
                "integerExpression",
                "Integer expression",
                IntegerType,
                BinaryOperation(
                  Add,
                  BinaryOperation(Add, Reference("integerConstant"), IntAnswer(Some(1))),
                  Reference("integerVariable")
                )
              ),
              Question(
                "decimalExpression",
                "Decimal expression",
                DecimalType,
                BinaryOperation(
                  Add,
                  BinaryOperation(Add, Reference("decimalConstant"), DecAnswer(1.0)),
                  Reference("decimalVariable")
                )
              )
            )
          )
        )
      )
      val inputs: Dictionary = Map(
        "integerConstant" -> IntAnswer(42),
        "decimalExpression" -> DecAnswer(None),
        "decimalVariable" -> DecAnswer(None),
        "booleanVariable" -> BooleanAnswer(false),
        "integerExpression" -> IntAnswer(None),
        "integerVariable" -> IntAnswer(12),
        "decimalConstant" -> DecAnswer(42.4)
      )

      val result = Evaluator.evaluate(qlForm, inputs)
      val expected: Dictionary =
        Map(
          "integerConstant" -> IntAnswer(42),
          "decimalExpression" -> DecAnswer(None),
          "decimalVariable" -> DecAnswer(None),
          "booleanVariable" -> BooleanAnswer(false),
          "integerExpression" -> IntAnswer(55),
          "integerVariable" -> IntAnswer(12),
          "decimalConstant" -> DecAnswer(42.4)
        )

      assert(result == expected)
    }
  }
}

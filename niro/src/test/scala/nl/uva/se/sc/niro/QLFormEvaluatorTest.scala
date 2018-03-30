package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.ql.evaluation.QLFormEvaluator
import nl.uva.se.sc.niro.model.ql.evaluation.QLFormEvaluator.Dictionary
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ BooleanAnswer, DateAnswer, DecimalAnswer, IntegerAnswer }
import org.scalatest.WordSpec

class QLFormEvaluatorTest extends WordSpec {

  "QLFromEvaluator" should {
    "evaluate a QLForm with single expression" in {
      val qLForm = QLForm(
        formName = "Revenue",
        statements = List(
          Question("revenue", "How much did you earn", IntegerType, Some(IntegerAnswer(1000))),
          Question("expenses", "How much did you earn", IntegerType, Some(IntegerAnswer(800))),
          Conditional(
            BooleanAnswer(true),
            Seq(
              Question(
                "profit",
                "You still have",
                IntegerType,
                Some(Subtract(Reference("revenue"), Reference("expenses"))))
            ))
        )
      )

      val result = QLFormEvaluator.evaluate(qLForm, Map.empty)
      val expected =
        Map(
          "revenue" -> IntegerAnswer(1000),
          "expenses" -> IntegerAnswer(800),
          "profit" -> IntegerAnswer(200)
        )

      assert(result == expected)
    }

    "evaluate a QLForm with multiple expressions" in {
      val qlForm = QLForm(
        "EditOrNotToEdit",
        List(
          Question("booleanVariable", "Boolean variable", BooleanType, None),
          Question("integerVariable", "Integer variable", IntegerType, None),
          Question("decimalVariable", "Decimal variable", DecimalType, None),
          Conditional(
            Reference("booleanVariable"),
            List(
              Question(
                "integerConstant",
                "Integer constant",
                IntegerType,
                Some(Multiply(IntegerAnswer(21), IntegerAnswer(2)))
              ),
              Question("decimalConstant", "Decimal constant", DecimalType, Some(DecimalAnswer(42.4)))
            )
          ),
          Conditional(
            Negate(Reference("booleanVariable")),
            List(
              Question(
                "integerExpression",
                "Integer expression",
                IntegerType,
                Some(
                  Addition(
                    Addition(Reference("integerConstant"), IntegerAnswer(1)),
                    Reference("integerVariable")
                  ))
              ),
              Question(
                "decimalExpression",
                "Decimal expression",
                DecimalType,
                Some(
                  Addition(
                    Addition(Reference("decimalConstant"), DecimalAnswer(1.0)),
                    Reference("decimalVariable")
                  ))
              )
            )
          )
        )
      )

      val inputs: Dictionary = Map(
        "booleanVariable" -> BooleanAnswer(false),
        "integerVariable" -> IntegerAnswer(12),
        "decimalConstant" -> DecimalAnswer(42.4)
      )

      val result = QLFormEvaluator.evaluate(qlForm, inputs)
      val expected: Dictionary =
        Map(
          "integerConstant" -> IntegerAnswer(42),
          "booleanVariable" -> BooleanAnswer(false),
          "integerExpression" -> IntegerAnswer(55),
          "integerVariable" -> IntegerAnswer(12),
          "decimalConstant" -> DecimalAnswer(42.4)
        )

      assert(result == expected)
    }

    "re-evaluate a QLForm" in {
      val qlForm = QLForm(
        "EditOrNotToEdit",
        List(
          Question("integerVariable", "Integer variable", IntegerType, None),
          Question("dateVariable", "Date variable", DateType, None),
          Question(
            "integerConstant",
            "Integer constant",
            IntegerType,
            Some(Multiply(IntegerAnswer(21), IntegerAnswer(2)))
          ),
          Question("dateConstant", "Date constant", DateType, Some(DateAnswer("1970-01-01"))),
          Question(
            "integerExpression",
            "Integer expression",
            IntegerType,
            Some(
              Addition(
                Addition(Reference("integerConstant"), IntegerAnswer(1)),
                Reference("integerVariable")
              ))
          ),
          Question("dateExpression", "Date expression", DateType, Some(Reference("dateVariable")))
        )
      )

      val inputs: Dictionary = Map(
        "dateConstant" -> DateAnswer("1970-01-01"),
        "integerVariable" -> IntegerAnswer(123),
        "integerConstant" -> IntegerAnswer(42))

      val result = QLFormEvaluator.evaluate(qlForm, inputs)
      val expected: Dictionary =
        Map(
          "dateConstant" -> DateAnswer("1970-01-01"),
          "integerVariable" -> IntegerAnswer(123),
          "integerConstant" -> IntegerAnswer(42),
          "integerExpression" -> IntegerAnswer(166)
        )

      assert(result == expected, "First pass")

      val alteredInput: Dictionary =
        Map(
          "dateConstant" -> DateAnswer("1970-01-01"),
          "integerVariable" -> IntegerAnswer(456),
          "integerConstant" -> IntegerAnswer(42),
          "integerExpression" -> IntegerAnswer(166)
        )

      val alteredResult = QLFormEvaluator.evaluate(qlForm, alteredInput)
      val alteredExpected: Dictionary =
        Map(
          "dateConstant" -> DateAnswer("1970-01-01"),
          "integerConstant" -> IntegerAnswer(42),
          "integerExpression" -> IntegerAnswer(499),
          "integerVariable" -> IntegerAnswer(456)
        )

      assert(alteredResult == alteredExpected, "Second pass")
    }

    "re-evaluate another QLForm" in {
      val qlForm = QLForm(
        "EditOrNotToEdit",
        List(
          Question("a", "a", IntegerType, None),
          Question("b", "b", IntegerType, Some(Reference("a"))),
          Question("c", "c", IntegerType, Some(Reference("b")))
        )
      )

      val inputs: Dictionary = Map(
        "a" -> IntegerAnswer(1)
      )

      val result = QLFormEvaluator.evaluate(qlForm, inputs)
      val expected: Dictionary =
        Map(
          "a" -> IntegerAnswer(1),
          "b" -> IntegerAnswer(1),
          "c" -> IntegerAnswer(1)
        )

      assert(result == expected, "First pass")

      val alteredInput: Dictionary =
        Map(
          "b" -> IntegerAnswer(1),
          "c" -> IntegerAnswer(1)
        )

      val alteredResult = QLFormEvaluator.evaluate(qlForm, alteredInput)
      val alteredExpected: Dictionary =
        Map()

      assert(alteredResult == alteredExpected, "Second pass")
    }
  }
}

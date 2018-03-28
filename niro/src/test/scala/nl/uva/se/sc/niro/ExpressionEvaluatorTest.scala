package nl.uva.se.sc.niro

import java.time.LocalDate

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.evaluation.ExpressionEvaluator._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{ Matchers, WordSpec }

class ExpressionEvaluatorTest extends WordSpec with Matchers with TableDrivenPropertyChecks {

  "The Expression evaluator" can {
    "do basic arithmetic operations" should {
      "on integers" in {
        val table = Table(
          ("Expression", "Expected Answer"),
          (Addition(IntegerAnswer(5), IntegerAnswer(3)), IntegerAnswer(8)),
          (Subtract(IntegerAnswer(5), IntegerAnswer(3)), IntegerAnswer(2)),
          (Multiply(IntegerAnswer(5), IntegerAnswer(3)), IntegerAnswer(15)),
          (Divide(IntegerAnswer(10), IntegerAnswer(5)), IntegerAnswer(2))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      "on decimals" in {
        val table = Table(
          ("Expression", "Expected Answer"),
          (Addition(DecimalAnswer(5), DecimalAnswer(3)), DecimalAnswer(8)),
          (Subtract(DecimalAnswer(5), DecimalAnswer(3)), DecimalAnswer(2)),
          (Multiply(DecimalAnswer(5), DecimalAnswer(3)), DecimalAnswer(15)),
          (Divide(DecimalAnswer(10), DecimalAnswer(5)), DecimalAnswer(2))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      "on money" in {
        val table = Table(
          ("Expression", "Expected Answer"),
          (Addition(MoneyAnswer(5), MoneyAnswer(3)), MoneyAnswer(8)),
          (Subtract(MoneyAnswer(5), MoneyAnswer(3)), MoneyAnswer(2)),
          (Divide(MoneyAnswer(10), MoneyAnswer(5)), DecimalAnswer(2))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      // TODO fix money arithmetics (see commented lines)
      "on different types" in {
        val table = Table(
          ("Expression", "Expected Answer"),
          (Multiply(MoneyAnswer(5), IntegerAnswer(3)), MoneyAnswer(15)),
          (Multiply(MoneyAnswer(5), DecimalAnswer(3)), MoneyAnswer(15)),
//          (Multiply(IntegerAnswer(3), MoneyAnswer(5)), MoneyAnswer(15)),
//          (Multiply(DecimalAnswer(3), MoneyAnswer(5)), MoneyAnswer(15)),
          (Divide(MoneyAnswer(10), IntegerAnswer(5)), MoneyAnswer(2)),
          (Divide(MoneyAnswer(10), DecimalAnswer(5)), MoneyAnswer(2)),
          (Addition(IntegerAnswer(10), DecimalAnswer(5)), DecimalAnswer(15)),
          (Addition(DecimalAnswer(10), IntegerAnswer(5)), DecimalAnswer(15)),
          (Subtract(IntegerAnswer(10), DecimalAnswer(6)), DecimalAnswer(4)),
          (Subtract(DecimalAnswer(10), IntegerAnswer(6)), DecimalAnswer(4)),
          (Multiply(IntegerAnswer(10), DecimalAnswer(5)), DecimalAnswer(50)),
          (Multiply(DecimalAnswer(10), IntegerAnswer(5)), DecimalAnswer(50)),
          (Divide(IntegerAnswer(10), DecimalAnswer(5)), DecimalAnswer(2)),
          (Divide(DecimalAnswer(10), IntegerAnswer(5)), DecimalAnswer(2))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      "gracefully handle divide by zero errors" in {
        val table = Table(
          ("Expression", "Expected Answer"),
          (Divide(IntegerAnswer(5), IntegerAnswer(0)), None),
          (Divide(DecimalAnswer(5), DecimalAnswer(0)), None),
          (Divide(MoneyAnswer(5), MoneyAnswer(0)), None)
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(expectedAnswer)
        }
      }
    }

    "do comparison operations" should {
      "on strings" in {
        val table = Table(
          ("Operator", "Expected Answer"),
          (LessThan(StringAnswer("A"), StringAnswer("B")), BooleanAnswer(true)),
          (LessThan(StringAnswer("B"), StringAnswer("A")), BooleanAnswer(false)),
          (LessThanEqual(StringAnswer("A"), StringAnswer("B")), BooleanAnswer(true)),
          (LessThanEqual(StringAnswer("A"), StringAnswer("A")), BooleanAnswer(true)),
          (LessThanEqual(StringAnswer("B"), StringAnswer("A")), BooleanAnswer(false)),
          (GreaterThenEqual(StringAnswer("B"), StringAnswer("A")), BooleanAnswer(true)),
          (GreaterThenEqual(StringAnswer("A"), StringAnswer("A")), BooleanAnswer(true)),
          (GreaterThenEqual(StringAnswer("A"), StringAnswer("B")), BooleanAnswer(false)),
          (GreaterThen(StringAnswer("B"), StringAnswer("A")), BooleanAnswer(true)),
          (GreaterThen(StringAnswer("A"), StringAnswer("B")), BooleanAnswer(false)),
          (NotEqual(StringAnswer("A"), StringAnswer("B")), BooleanAnswer(true)),
          (NotEqual(StringAnswer("A"), StringAnswer("A")), BooleanAnswer(false)),
          (Equal(StringAnswer("A"), StringAnswer("A")), BooleanAnswer(true)),
          (Equal(StringAnswer("A"), StringAnswer("B")), BooleanAnswer(false))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      "on integers" in {
        val table = Table(
          ("Operator", "Expected Answer"),
          (LessThan(IntegerAnswer(1), IntegerAnswer(2)), BooleanAnswer(true)),
          (LessThan(IntegerAnswer(2), IntegerAnswer(1)), BooleanAnswer(false)),
          (LessThanEqual(IntegerAnswer(1), IntegerAnswer(2)), BooleanAnswer(true)),
          (LessThanEqual(IntegerAnswer(1), IntegerAnswer(1)), BooleanAnswer(true)),
          (LessThanEqual(IntegerAnswer(2), IntegerAnswer(1)), BooleanAnswer(false)),
          (GreaterThenEqual(IntegerAnswer(5), IntegerAnswer(3)), BooleanAnswer(true)),
          (GreaterThenEqual(IntegerAnswer(5), IntegerAnswer(5)), BooleanAnswer(true)),
          (GreaterThenEqual(IntegerAnswer(3), IntegerAnswer(5)), BooleanAnswer(false)),
          (GreaterThen(IntegerAnswer(5), IntegerAnswer(3)), BooleanAnswer(true)),
          (GreaterThen(IntegerAnswer(3), IntegerAnswer(5)), BooleanAnswer(false)),
          (NotEqual(IntegerAnswer(5), IntegerAnswer(3)), BooleanAnswer(true)),
          (NotEqual(IntegerAnswer(5), IntegerAnswer(5)), BooleanAnswer(false)),
          (Equal(IntegerAnswer(5), IntegerAnswer(5)), BooleanAnswer(true)),
          (Equal(IntegerAnswer(5), IntegerAnswer(3)), BooleanAnswer(false))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      "on booleans" in {
        val table = Table(
          ("Operator", "Expected Answer"),
          (LessThan(BooleanAnswer(false), BooleanAnswer(true)), BooleanAnswer(true)),
          (LessThan(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(false)),
          (LessThanEqual(BooleanAnswer(false), BooleanAnswer(true)), BooleanAnswer(true)),
          (LessThanEqual(BooleanAnswer(true), BooleanAnswer(true)), BooleanAnswer(true)),
          (LessThanEqual(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(false)),
          (GreaterThenEqual(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(true)),
          (GreaterThenEqual(BooleanAnswer(true), BooleanAnswer(true)), BooleanAnswer(true)),
          (GreaterThenEqual(BooleanAnswer(false), BooleanAnswer(true)), BooleanAnswer(false)),
          (GreaterThen(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(true)),
          (GreaterThen(BooleanAnswer(true), BooleanAnswer(true)), BooleanAnswer(false)),
          (NotEqual(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(true)),
          (NotEqual(BooleanAnswer(true), BooleanAnswer(true)), BooleanAnswer(false)),
          (Equal(BooleanAnswer(true), BooleanAnswer(true)), BooleanAnswer(true)),
          (Equal(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(false))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      "on dates" in {
        val table = Table(
          ("Operator", "Expected Answer"),
          // format: off
          (LessThan(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-09"))), BooleanAnswer(true)),
          (LessThan(DateAnswer(LocalDate.parse("2018-03-09")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(false)),
          (LessThanEqual(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-09"))), BooleanAnswer(true)),
          (LessThanEqual(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(true)),
          (LessThanEqual(DateAnswer(LocalDate.parse("2018-03-09")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(false)),
          (GreaterThenEqual(DateAnswer(LocalDate.parse("2018-03-09")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(true)),
          (GreaterThenEqual(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(true)),
          (GreaterThenEqual(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-09"))), BooleanAnswer(false)),
          (GreaterThen(DateAnswer(LocalDate.parse("2018-03-09")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(true)),
          (GreaterThen(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-09"))), BooleanAnswer(false)),
          (NotEqual(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-09"))), BooleanAnswer(true)),
          (NotEqual(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(false)),
          (Equal(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-08"))), BooleanAnswer(true)),
          (Equal(DateAnswer(LocalDate.parse("2018-03-08")), DateAnswer(LocalDate.parse("2018-03-09"))), BooleanAnswer(false))
          // format: on
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }

      "on money" in {
        val table = Table(
          ("Operator", "Expected Answer"),
          (LessThan(MoneyAnswer(1), MoneyAnswer(2)), BooleanAnswer(true)),
          (LessThan(MoneyAnswer(2), MoneyAnswer(1)), BooleanAnswer(false)),
          (LessThanEqual(MoneyAnswer(1), MoneyAnswer(2)), BooleanAnswer(true)),
          (LessThanEqual(MoneyAnswer(1), MoneyAnswer(1)), BooleanAnswer(true)),
          (LessThanEqual(MoneyAnswer(2), MoneyAnswer(1)), BooleanAnswer(false)),
          (GreaterThenEqual(MoneyAnswer(5), MoneyAnswer(3)), BooleanAnswer(true)),
          (GreaterThenEqual(MoneyAnswer(5), MoneyAnswer(5)), BooleanAnswer(true)),
          (GreaterThenEqual(MoneyAnswer(3), MoneyAnswer(5)), BooleanAnswer(false)),
          (GreaterThen(MoneyAnswer(5), MoneyAnswer(3)), BooleanAnswer(true)),
          (GreaterThen(MoneyAnswer(3), MoneyAnswer(5)), BooleanAnswer(false)),
          (NotEqual(MoneyAnswer(5), MoneyAnswer(3)), BooleanAnswer(true)),
          (NotEqual(MoneyAnswer(5), MoneyAnswer(5)), BooleanAnswer(false)),
          (Equal(MoneyAnswer(5), MoneyAnswer(5)), BooleanAnswer(true)),
          (Equal(MoneyAnswer(5), MoneyAnswer(3)), BooleanAnswer(false))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }
    }

    "do logical operations" should {
      "on booleans" in {
        val table = Table(
          ("Operator", "Expected Answer"),
          (Or(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(true)),
          (Or(BooleanAnswer(false), BooleanAnswer(false)), BooleanAnswer(false)),
          (And(BooleanAnswer(true), BooleanAnswer(true)), BooleanAnswer(true)),
          (And(BooleanAnswer(true), BooleanAnswer(false)), BooleanAnswer(false))
        )

        forAll(table) { (expression, expectedAnswer) =>
          expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
        }
      }
    }

    "do nested operations" in {
      val table = Table(
        ("Operator", "Expected Answer"),
        (Multiply(IntegerAnswer(5), Multiply(IntegerAnswer(10), IntegerAnswer(15))), IntegerAnswer(750)),
        (And(BooleanAnswer(true), And(BooleanAnswer(true), BooleanAnswer(true))), BooleanAnswer(true)),
        (And(BooleanAnswer(true), Equal(StringAnswer("Foo"), StringAnswer("Foo"))), BooleanAnswer(true))
      )

      forAll(table) { (expression, expectedAnswer) =>
        expression.evaluate(Map.empty, Map.empty) should be(Some(expectedAnswer))
      }
    }

    "correctly find references" in {
      val qlForm = QLForm(
        formName = "Revenue",
        statements = List(
          Question("revenue", "How much did you earn", IntegerType, Some(IntegerAnswer(1000))),
          Question("expenses", "How much did you spend", IntegerType, Some(IntegerAnswer(200))),
          Question("profit", "You still have", IntegerType, Some(Subtract(Reference("revenue"), Reference("expenses"))))
        )
      )

      val questions: Seq[Question] = qlForm.statements.collect { case q: Question => q }
      val result = questions.flatMap(question => question.expression.map(_.evaluate(qlForm.symbolTable, Map.empty)))
      assert(result == Seq(Some(IntegerAnswer(1000)), Some(IntegerAnswer(200)), Some(IntegerAnswer(800))))
    }

    "do error handling" should {
      "throw an error for arithmetic operations on unsupported types" in {
        val expression = Divide(BooleanAnswer(true), BooleanAnswer(true))

        assertThrows[UnsupportedOperationException](expression.evaluate(Map.empty, Map.empty))
      }
      "throw an error for logical operations on unsupported types" in {
        val expression = And(StringAnswer("Foo"), StringAnswer("Bar"))

        assertThrows[UnsupportedOperationException](expression.evaluate(Map.empty, Map.empty))
      }
      "throw an error when evaluating mixed answertypes" in {
        val expression = Equal(BooleanAnswer(true), IntegerAnswer(5))

        assertThrows[MatchError](expression.evaluate(Map.empty, Map.empty))
      }
    }
  }
}

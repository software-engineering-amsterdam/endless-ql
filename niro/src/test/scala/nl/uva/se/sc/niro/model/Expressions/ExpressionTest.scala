package nl.uva.se.sc.niro.model.Expressions

import nl.uva.se.sc.niro.model.Expressions.Expression._
import nl.uva.se.sc.niro.model.Expressions.answers._
import nl.uva.se.sc.niro.model._
import org.scalatest.{ Matchers, WordSpec }

class ExpressionTest extends WordSpec with Matchers {
  "The Expression evaluator" can {
    "do basic arithmetic operations" should {
      "add" in {
        val expression = BinaryOperation(Add, IntAnswer(Some(5)), IntAnswer(Some(3)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(IntAnswer(Some(8)))
      }
      "subtract" in {
        val expression = BinaryOperation(Sub, IntAnswer(Some(5)), IntAnswer(Some(3)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(IntAnswer(Some(2)))
      }
      "multiply" in {
        val expression = BinaryOperation(Mul, IntAnswer(Some(5)), IntAnswer(Some(3)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(IntAnswer(Some(15)))
      }
      "divide" in {
        val expression = BinaryOperation(Div, IntAnswer(Some(10)), IntAnswer(Some(5)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(IntAnswer(Some(2)))
      }
      // TODO deal with div by zero error
//      "return None when dividing by zero" in {
//        val expression = BinaryOperation(Div, IntAnswer(Some(10)), IntAnswer(Some(0)))
//
//        val answer: Answer = evaluate(expression, Map.empty)
//
//        answer should be(None)
//      }
    }

    "do comparison operations" should {
      "on strings Lt positive" in {
        val expression = BinaryOperation(Lt, StringAnswer(Some("A")), StringAnswer(Some("B")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on strings Lt negative" in {
        val expression = BinaryOperation(Lt, StringAnswer(Some("B")), StringAnswer(Some("A")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on strings LTe positive" in {
        val expression = BinaryOperation(LTe, StringAnswer(Some("A")), StringAnswer(Some("A")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on strings LTe negative" in {
        val expression = BinaryOperation(LTe, StringAnswer(Some("B")), StringAnswer(Some("A")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on strings GTe positive" in {
        val expression = BinaryOperation(GTe, StringAnswer(Some("B")), StringAnswer(Some("B")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on strings GTe negative" in {
        val expression = BinaryOperation(GTe, StringAnswer(Some("A")), StringAnswer(Some("B")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on strings Gt positive" in {
        val expression = BinaryOperation(Gt, StringAnswer(Some("B")), StringAnswer(Some("A")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on strings Gt negative" in {
        val expression = BinaryOperation(Gt, StringAnswer(Some("A")), StringAnswer(Some("B")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on strings Ne positive" in {
        val expression = BinaryOperation(Ne, StringAnswer(Some("A")), StringAnswer(Some("B")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on strings Ne negative" in {
        val expression = BinaryOperation(Ne, StringAnswer(Some("A")), StringAnswer(Some("A")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on strings Eq positive" in {
        val expression = BinaryOperation(Eq, StringAnswer(Some("A")), StringAnswer(Some("A")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on strings Eq negative" in {
        val expression = BinaryOperation(Eq, StringAnswer(Some("A")), StringAnswer(Some("B")))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on integers Lt positive" in {
        val expression = BinaryOperation(Lt, IntAnswer(Some(1)), IntAnswer(Some(2)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on integers Lt negative" in {
        val expression = BinaryOperation(Lt, IntAnswer(Some(2)), IntAnswer(Some(1)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on integers LTe positive" in {
        val expression = BinaryOperation(LTe, IntAnswer(Some(1)), IntAnswer(Some(1)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on integers LTe negative" in {
        val expression = BinaryOperation(LTe, IntAnswer(Some(2)), IntAnswer(Some(1)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on integers GTe positive" in {
        val expression = BinaryOperation(GTe, IntAnswer(Some(5)), IntAnswer(Some(3)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on integers GTe negative" in {
        val expression = BinaryOperation(GTe, IntAnswer(Some(3)), IntAnswer(Some(5)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on integers Gt positive" in {
        val expression = BinaryOperation(Gt, IntAnswer(Some(5)), IntAnswer(Some(3)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on integers Gt negative" in {
        val expression = BinaryOperation(Gt, IntAnswer(Some(3)), IntAnswer(Some(5)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on integers Ne positive" in {
        val expression = BinaryOperation(Ne, IntAnswer(Some(5)), IntAnswer(Some(3)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on integers Ne negative" in {
        val expression = BinaryOperation(Ne, IntAnswer(Some(5)), IntAnswer(Some(5)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on integers Eq positive" in {
        val expression = BinaryOperation(Eq, IntAnswer(Some(5)), IntAnswer(Some(5)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on integers Eq negative" in {
        val expression = BinaryOperation(Eq, IntAnswer(Some(5)), IntAnswer(Some(3)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on booleans Lt positive" in {
        val expression = BinaryOperation(Lt, BooleanAnswer(Some(false)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on booleans Lt negative" in {
        val expression = BinaryOperation(Lt, BooleanAnswer(Some(true)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on booleans LTe positive" in {
        val expression = BinaryOperation(LTe, BooleanAnswer(Some(true)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on booleans LTe negative" in {
        val expression = BinaryOperation(LTe, BooleanAnswer(Some(true)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on booleans GTe positive" in {
        val expression = BinaryOperation(GTe, BooleanAnswer(Some(true)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on booleans GTe negative" in {
        val expression = BinaryOperation(GTe, BooleanAnswer(Some(false)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on booleans Gt positive" in {
        val expression = BinaryOperation(Gt, BooleanAnswer(Some(true)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on booleans Gt negative" in {
        val expression = BinaryOperation(Gt, BooleanAnswer(Some(true)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on booleans Ne positive" in {
        val expression = BinaryOperation(Ne, BooleanAnswer(Some(true)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on booleans Ne negative" in {
        val expression = BinaryOperation(Ne, BooleanAnswer(Some(true)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "on booleans Eq positive" in {
        val expression = BinaryOperation(Eq, BooleanAnswer(Some(true)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "on booleans Eq negative" in {
        val expression = BinaryOperation(Eq, BooleanAnswer(Some(true)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
    }
    "do logical operations" should {
      "Or positive" in {
        val expression = BinaryOperation(Or, BooleanAnswer(Some(true)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "Or negative" in {
        val expression = BinaryOperation(Or, BooleanAnswer(Some(false)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
      "And positive" in {
        val expression = BinaryOperation(And, BooleanAnswer(Some(true)), BooleanAnswer(Some(true)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "And negative" in {
        val expression = BinaryOperation(And, BooleanAnswer(Some(true)), BooleanAnswer(Some(false)))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(false)))
      }
    }
    "do nested operations" should {
      "nested arithmetic operation" in {
        val expression = BinaryOperation(Mul, IntAnswer(Some(5)), BinaryOperation(Mul, IntAnswer(Some(5)), IntAnswer(Some(5))))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(IntAnswer(Some(125)))
      }
      "nested logical operation" in {
        val expression = BinaryOperation(And, BooleanAnswer(Some(true)), BinaryOperation(And, BooleanAnswer(Some(true)), BooleanAnswer(Some(true))))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
      "nested mixed operation" in {
        val expression = BinaryOperation(And, BooleanAnswer(Some(true)), BinaryOperation(Eq, StringAnswer(Some("Foo")), StringAnswer(Some("Foo"))))

        val answer: Answer = evaluate(expression, Map.empty)

        answer should be(BooleanAnswer(Some(true)))
      }
    }
    "correctly find references" in {
      val qlForm = QLForm(
        formName = "Revenue",
        statements = List(
          Question("revenue","How much did you earn", IntAnswer(Some(1000))),
          Question("expenses", "How much did you spend", IntAnswer(Some(200))),
          Question("profit", "You still have", BinaryOperation(Sub, Reference("revenue"), Reference("expenses")))
        )
      )

      val q: Seq[Question] = qlForm.statements.collect{ case q: Question => q }
      val x =  q.map(q => evaluate(q.answer, qlForm.symbolTable))
      assert(x == Seq(IntAnswer(Some(1000)), IntAnswer(Some(200)), IntAnswer(Some(800))))
    }
    "do error handling" should {
      "throw an error for arithmetic operations on unsupported types" in {
        val expression = BinaryOperation(Div, BooleanAnswer(Some(true)), BooleanAnswer(Some(true)))

        assertThrows[UnsupportedOperationException](evaluate(expression, Map.empty))
      }
      "throw an error for logical operations on unsupported types" in {
        val expression = BinaryOperation(And, StringAnswer(Some("Foo")), StringAnswer(Some("Bar")))

        assertThrows[UnsupportedOperationException](evaluate(expression, Map.empty))
      }
      "throw an error when evaluating mixed answertypes" in {
        val expression = BinaryOperation(Eq, BooleanAnswer(Some(true)), IntAnswer(Some(5)))

        assertThrows[IllegalArgumentException](evaluate(expression, Map.empty))
      }
    }
  }
}
package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.Evaluator.evaluateExpression
import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer, StringAnswer }
import nl.uva.se.sc.niro.model.Expressions.{ Answer, BinaryOperation, Reference }
import nl.uva.se.sc.niro.model._
import org.scalatest.{ Matchers, WordSpec }

class ExpressionEvaluatorTest extends WordSpec with Matchers {

  "The Expression evaluator" can {
    "do basic arithmetic operations" should {
      "add" in {
        val expression = BinaryOperation(Add, IntAnswer(5), IntAnswer(3))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(IntAnswer(8))
      }
      "subtract" in {
        val expression = BinaryOperation(Sub, IntAnswer(5), IntAnswer(3))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(IntAnswer(2))
      }
      "multiply" in {
        val expression = BinaryOperation(Mul, IntAnswer(5), IntAnswer(3))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(IntAnswer(15))
      }
      "divide" in {
        val expression = BinaryOperation(Div, IntAnswer(10), IntAnswer(5))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(IntAnswer(2))
      }
      // TODO deal with div by zero error
      //      "return None when dividing by zero" in {
      //        val expression = BinaryOperation(Div, IntAnswer(10), IntAnswer(0)))
      //
      //        val answer: Answer = evaluate(expression, Map.empty)
      //
      //        answer should be(None)
      //      }
    }

    "do comparison operations" should {
      "on strings Lt positive" in {
        val expression = BinaryOperation(Lt, StringAnswer("A"), StringAnswer("B"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on strings Lt negative" in {
        val expression = BinaryOperation(Lt, StringAnswer("B"), StringAnswer("A"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on strings LTe positive" in {
        val expression = BinaryOperation(LTe, StringAnswer("A"), StringAnswer("A"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on strings LTe negative" in {
        val expression = BinaryOperation(LTe, StringAnswer("B"), StringAnswer("A"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on strings GTe positive" in {
        val expression = BinaryOperation(GTe, StringAnswer("B"), StringAnswer("B"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on strings GTe negative" in {
        val expression = BinaryOperation(GTe, StringAnswer("A"), StringAnswer("B"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on strings Gt positive" in {
        val expression = BinaryOperation(Gt, StringAnswer("B"), StringAnswer("A"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on strings Gt negative" in {
        val expression = BinaryOperation(Gt, StringAnswer("A"), StringAnswer("B"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on strings Ne positive" in {
        val expression = BinaryOperation(Ne, StringAnswer("A"), StringAnswer("B"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on strings Ne negative" in {
        val expression = BinaryOperation(Ne, StringAnswer("A"), StringAnswer("A"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on strings Eq positive" in {
        val expression = BinaryOperation(Eq, StringAnswer("A"), StringAnswer("A"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on strings Eq negative" in {
        val expression = BinaryOperation(Eq, StringAnswer("A"), StringAnswer("B"))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on integers Lt positive" in {
        val expression = BinaryOperation(Lt, IntAnswer(1), IntAnswer(2))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on integers Lt negative" in {
        val expression = BinaryOperation(Lt, IntAnswer(2), IntAnswer(1))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on integers LTe positive" in {
        val expression = BinaryOperation(LTe, IntAnswer(1), IntAnswer(1))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on integers LTe negative" in {
        val expression = BinaryOperation(LTe, IntAnswer(2), IntAnswer(1))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on integers GTe positive" in {
        val expression = BinaryOperation(GTe, IntAnswer(5), IntAnswer(3))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on integers GTe negative" in {
        val expression = BinaryOperation(GTe, IntAnswer(3), IntAnswer(5))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on integers Gt positive" in {
        val expression = BinaryOperation(Gt, IntAnswer(5), IntAnswer(3))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on integers Gt negative" in {
        val expression = BinaryOperation(Gt, IntAnswer(3), IntAnswer(5))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on integers Ne positive" in {
        val expression = BinaryOperation(Ne, IntAnswer(5), IntAnswer(3))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on integers Ne negative" in {
        val expression = BinaryOperation(Ne, IntAnswer(5), IntAnswer(5))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on integers Eq positive" in {
        val expression = BinaryOperation(Eq, IntAnswer(5), IntAnswer(5))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on integers Eq negative" in {
        val expression = BinaryOperation(Eq, IntAnswer(5), IntAnswer(3))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on booleans Lt positive" in {
        val expression = BinaryOperation(Lt, BooleanAnswer(false), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on booleans Lt negative" in {
        val expression = BinaryOperation(Lt, BooleanAnswer(true), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on booleans LTe positive" in {
        val expression = BinaryOperation(LTe, BooleanAnswer(true), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on booleans LTe negative" in {
        val expression = BinaryOperation(LTe, BooleanAnswer(true), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on booleans GTe positive" in {
        val expression = BinaryOperation(GTe, BooleanAnswer(true), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on booleans GTe negative" in {
        val expression = BinaryOperation(GTe, BooleanAnswer(false), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on booleans Gt positive" in {
        val expression = BinaryOperation(Gt, BooleanAnswer(true), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on booleans Gt negative" in {
        val expression = BinaryOperation(Gt, BooleanAnswer(true), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on booleans Ne positive" in {
        val expression = BinaryOperation(Ne, BooleanAnswer(true), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on booleans Ne negative" in {
        val expression = BinaryOperation(Ne, BooleanAnswer(true), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "on booleans Eq positive" in {
        val expression = BinaryOperation(Eq, BooleanAnswer(true), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "on booleans Eq negative" in {
        val expression = BinaryOperation(Eq, BooleanAnswer(true), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
    }
    "do logical operations" should {
      "Or positive" in {
        val expression = BinaryOperation(Or, BooleanAnswer(true), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "Or negative" in {
        val expression = BinaryOperation(Or, BooleanAnswer(false), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
      "And positive" in {
        val expression = BinaryOperation(And, BooleanAnswer(true), BooleanAnswer(true))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "And negative" in {
        val expression = BinaryOperation(And, BooleanAnswer(true), BooleanAnswer(false))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(false))
      }
    }
    "do nested operations" should {
      "nested arithmetic operation" in {
        val expression = BinaryOperation(Mul, IntAnswer(5), BinaryOperation(Mul, IntAnswer(5), IntAnswer(5)))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(IntAnswer(125))
      }
      "nested logical operation" in {
        val expression = BinaryOperation(And, BooleanAnswer(true), BinaryOperation(And, BooleanAnswer(true), BooleanAnswer(true)))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
      "nested mixed operation" in {
        val expression = BinaryOperation(And, BooleanAnswer(true), BinaryOperation(Eq, StringAnswer("Foo"), StringAnswer("Foo")))

        val answer: Answer = evaluateExpression(expression, Map.empty)

        answer should be(BooleanAnswer(true))
      }
    }
    "correctly find references" in {
      val qlForm = QLForm(
        formName = "Revenue",
        statements = List(
          Question("revenue","How much did you earn", IntAnswer(1000)),
          Question("expenses", "How much did you spend", IntAnswer(200)),
          Question("profit", "You still have", BinaryOperation(Sub, Reference("revenue"), Reference("expenses")))
        )
      )

      val q: Seq[Question] = qlForm.statements.collect{ case q: Question => q }
      val x =  q.map(q => evaluateExpression(q.answer, qlForm.symbolTable))
      assert(x == Seq(IntAnswer(1000), IntAnswer(200), IntAnswer(800)))
    }
    "do error handling" should {
      "throw an error for arithmetic operations on unsupported types" in {
        val expression = BinaryOperation(Div, BooleanAnswer(true), BooleanAnswer(true))

        assertThrows[UnsupportedOperationException](evaluateExpression(expression, Map.empty))
      }
      "throw an error for logical operations on unsupported types" in {
        val expression = BinaryOperation(And, StringAnswer("Foo"), StringAnswer("Bar"))

        assertThrows[UnsupportedOperationException](evaluateExpression(expression, Map.empty))
      }
      "throw an error when evaluating mixed answertypes" in {
        val expression = BinaryOperation(Eq, BooleanAnswer(true), IntAnswer(5))

        assertThrows[IllegalArgumentException](evaluateExpression(expression, Map.empty))
      }
    }
  }
}

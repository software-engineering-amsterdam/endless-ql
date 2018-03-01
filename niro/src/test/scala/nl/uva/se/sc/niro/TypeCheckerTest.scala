package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.Expressions.{ BinaryOperation, Reference, UnaryOperation }
import nl.uva.se.sc.niro.model.Expressions.answers.{ BooleanAnswer, IntAnswer }
import nl.uva.se.sc.niro.model._
import org.scalatest.WordSpec

class TypeCheckerTest extends WordSpec {

  "TypeCheckerTest" should {

    "checkDuplicateLabels" in {
      val qlForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "duplicate-label", IntegerType, IntAnswer(1), None),
          Question("q2", "duplicate-label", IntegerType, IntAnswer(1), None)
        ))

      assertThrows[IllegalArgumentException](TypeChecker.checkDuplicateLabels(qlForm))
    }

    "checkOperandsOfInvalidTypeToOperators" in {}

    "checkNonBooleanPredicates" in {
      val qLForm = QLForm(
        "duplicateLabel",
        Seq(
          Conditional(BinaryOperation(And, BooleanAnswer(true), BooleanAnswer(false)), Seq.empty),
          Conditional(BinaryOperation(Mul, IntAnswer(5), IntAnswer(1)), Seq.empty)
        )
      )

      assertThrows[IllegalArgumentException](TypeChecker.checkNonBooleanPredicates(qLForm))
    }

    "checkDuplicateQuestionDeclarationsWithDifferentTypes" in {
      val qLForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "duplicate identifier", IntegerType, IntAnswer(1), None),
          Question("q1", "duplicate identifier", BooleanType, IntAnswer(1), None)
        )
      )

      assertThrows[IllegalArgumentException](TypeChecker.checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm))
    }

    "checkCyclicDependenciesBetweenQuestions" ignore {
      val qlForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "question1", IntegerType, Reference("q3"), None),
          Question("q2", "question2", IntegerType, Reference("q1"), None),
          Question("q3", "question3", IntegerType, Reference("q2"), None)
        )
      )
      assertThrows[IllegalArgumentException](TypeChecker.checkReferences(qlForm))
    }

    "checkReferences" can {
      "throw an error when a reference to a non defined question is encountered" in {
        val qlForm = QLForm(
          "check references",
          Seq(
            Question("q1", "questions with undefined reference", IntegerType, Reference("1"), None),
            Question("q2", "question2", IntegerType, IntAnswer(1), None),
            Question("q3", "question3", IntegerType, Reference("q2"), None)
          )
        )
        assertThrows[IllegalArgumentException](TypeChecker.checkReferences(qlForm))
      }

      "throw an error when a reference to a non defined question is encountered somewhere in an expression" in {
        val qlForm = QLForm(
          "check references in expressions",
          Seq(
            Question(
              "q1",
              "questions with undefined reference",
              IntegerType,
              UnaryOperation(Min, Reference("1")),
              None),
            Question("q2", "question2", IntegerType, IntAnswer(1), None),
            Question("q3", "question3", IntegerType, Reference("q2"), None)
          )
        )
        assertThrows[IllegalArgumentException](TypeChecker.checkReferences(qlForm))
      }
    }

    "pipeline" in {
      val qLForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "question1", IntegerType, IntAnswer(1), None),
          Question("q2", "question2", IntegerType, IntAnswer(1), None),
          Question("q3", "question3", IntegerType, Reference("q2"), None)
        )
      )
      TypeChecker.pipeline(qLForm)
    }
  }
}

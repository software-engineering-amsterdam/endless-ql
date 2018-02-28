package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.Expressions.Reference
import nl.uva.se.sc.niro.model.Expressions.answers.IntAnswer
import nl.uva.se.sc.niro.model.{ IntegerType, QLForm, Question }
import org.scalatest.WordSpec

class TypeCheckerTest extends WordSpec {

  "TypeCheckerTest" should {

    "checkDuplicateLabels" in {
      val qlForm = QLForm("duplicateLabel",
        Seq(
          Question("q1", "duplicate-label", IntegerType, IntAnswer(1), None),
          Question("q2", "duplicate-label", IntegerType, IntAnswer(1), None)
        )
      )

      assertThrows[IllegalArgumentException](TypeChecker.checkDuplicateLabels(qlForm))
    }

    "checkOperandsOfInvalidTypeToOperators" in {

    }

    "checkNonBooleanPredicates" in {

    }

    "checkDuplicateQuestionDeclarationsWithDifferentTypes" in {

    }

    "checkCyclicDependenciesBetweenQuestions" in {

    }

    "checkReferences" in {
      val qlForm = QLForm("duplicateLabel",
        Seq(
          Question("q1", "questions with undefined reference", IntegerType, Reference("1"), None),
          Question("q2", "question2", IntegerType, IntAnswer(1), None),
          Question("q3", "question3", IntegerType, Reference("q2"), None)
        )
      )
      assertThrows[IllegalArgumentException](TypeChecker.checkReferences(qlForm))
    }
  }
}

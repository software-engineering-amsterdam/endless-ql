package nl.uva.se.sc.niro

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

    }

  }
}

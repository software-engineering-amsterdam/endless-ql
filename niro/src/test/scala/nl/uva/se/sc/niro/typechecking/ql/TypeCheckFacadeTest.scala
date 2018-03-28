package nl.uva.se.sc.niro.typechecking.ql

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.errors.Warning
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import org.scalatest.WordSpec

class TypeCheckFacadeTest extends WordSpec {

  "TypeCheckerFacadeTest" should {

    "checkDuplicateLabels" in {
      val qlForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "duplicate-label", IntegerType, Some(IntegerAnswer(1))),
          Question("q2", "duplicate-label", IntegerType, Some(IntegerAnswer(1)))
        )
      )

      val result = TypeCheckFacade.pipeline(qlForm)

      assert(
        result === Right(
          QLForm(
            "duplicateLabel",
            List(
              Question("q1", "duplicate-label", IntegerType, Some(IntegerAnswer(1))),
              Question("q2", "duplicate-label", IntegerType, Some(IntegerAnswer(1)))
            ),
            List(Warning(message = "Questions q1, q2 have duplicate label: duplicate-label"))
          )
        ))
    }

    "checkOperandsOfInvalidTypeToOperators" can {
      "return error for operands of invalid type to operator" in {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, Some(Multiply(StringAnswer("Foo"), StringAnswer("Bar"))))
          ))

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(
          result === Left(
            List(TypeCheckError("TypeCheckError", "Not a valid expression: (StringAnswer(Foo) * StringAnswer(Bar))"))))
      }

      "return error for operands of invalid type to eachother" in {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, Some(Equal(StringAnswer("Foo"), IntegerAnswer(0))))
          ))

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(
          result === Left(
            List(TypeCheckError("TypeCheckError", "Not a valid expression: (StringAnswer(Foo) == IntegerAnswer(0))"))))
      }

      "return no error for operands valid types in nested operations" in {
        val qlForm = QLForm(
          "nestedOperation",
          Seq(
            Conditional(
              Negate(GreaterThen(IntegerAnswer(5), IntegerAnswer(10))),
              List.empty
            )
          )
        )

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(
          result === Right(
            QLForm(
              "nestedOperation",
              List(
                Conditional(
                  Negate(GreaterThen(IntegerAnswer(5), IntegerAnswer(10))),
                  List.empty
                )
              )
            )
          )
        )
      }

      "return no error for operands of different types but valid: Decimal and Integer" in {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, Some(Equal(DecimalAnswer(1), IntegerAnswer(2))))
          ))

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(result === Right(qlForm))
      }

      "return no error for operands of different types but valid: Money and Integer" in {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, Some(Equal(MoneyAnswer(1), IntegerAnswer(1))))
          ))

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(result === Right(qlForm))
      }
    }

    "checkNonBooleanPredicates" in {
      val qLForm = QLForm(
        "duplicateLabel",
        Seq(
          Conditional(And(BooleanAnswer(true), BooleanAnswer(false)), Seq.empty),
          Conditional(Multiply(IntegerAnswer(5), IntegerAnswer(1)), Seq.empty)
        )
      )

      val result = TypeCheckFacade.pipeline(qLForm)

      assert(
        result === Left(
          List(
            TypeCheckError(
              "TypeCheckError",
              "Non boolean predicate: (IntegerAnswer(5) * IntegerAnswer(1))"
            )
          )
        ))
    }

    "checkDuplicateQuestionDeclarationsWithDifferentTypes" in {
      val qLForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "duplicate identifier", IntegerType, Some(IntegerAnswer(1))),
          Question("q1", "duplicate identifier", BooleanType, Some(IntegerAnswer(1)))
        )
      )

      val result = TypeCheckFacade.pipeline(qLForm)

      assert(
        result === Left(
          List(
            TypeCheckError(
              "TypeCheckError",
              "Duplicate question declarations with different types: q1 -> IntegerType, q1 -> BooleanType"
            )
          )
        )
      )
    }

    "checkCyclicDependenciesBetweenQuestions" can {
      "return the AST when no cyclic references are found" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, Some(Reference("q2"))),
            Question("q2", "question2", IntegerType, Some(Reference("q3"))),
            Question("q3", "question3", IntegerType, Some(IntegerAnswer(1)))
          )
        )
        val result = TypeCheckFacade.pipeline(qlForm)

        assert(Right(qlForm) === result)
      }

      "and return an error when cyclic references are found" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, Some(Reference("q3"))),
            Question("q2", "question2", IntegerType, Some(Reference("q1"))),
            Question("q3", "question3", IntegerType, Some(Reference("q2")))
          )
        )
        val result = TypeCheckFacade.pipeline(qlForm)

        assert(
          result === Left(
            List(
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q1 -> q3 -> q2 -> q1"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q2 -> q1 -> q3 -> q2"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q3 -> q2 -> q1 -> q3")
            )
          )
        )
      }

      "and return an error when cyclic references are found inside expressions" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, Some(Reference("q3"))),
            Question("q2", "question2", IntegerType, Some(Reference("q1"))),
            Question("q3", "question3", IntegerType, Some(Minus(Reference("q2"))))
          )
        )

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(
          result === Left(
            List(
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q1 -> q3 -> q2 -> q1"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q2 -> q1 -> q3 -> q2"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q3 -> q2 -> q1 -> q3")
            )
          )
        )
      }

      "and return an error when cyclic references are found inside expressions with multiple paths" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, Some(Multiply(Reference("q2"), Reference("q3")))),
            Question("q2", "question2", IntegerType, Some(Reference("q3"))),
            Question("q2", "question2", IntegerType, Some(Reference("q1"))),
            Question("q3", "question3", IntegerType, Some(Minus(IntegerAnswer(1))))
          )
        )

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(
          result === Left(
            List(
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q1 -> q2 -> q1"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q2 -> q1 -> q2")
            )
          )
        )
      }

      "and return an error when multiple cyclic references are found" in {
        val qlForm = QLForm(
          "cyclicDependencies",
          Seq(
            Question("q1", "question1", IntegerType, Some(IntegerAnswer(1))),
            Question("q2", "question2", IntegerType, Some(Reference("q3"))),
            Question("q3", "question3", IntegerType, Some(Reference("q2"))),
            Question("q4", "question4", IntegerType, Some(Reference("q5"))),
            Question("q5", "question5", IntegerType, Some(Reference("q4")))
          )
        )

        val result = TypeCheckFacade.pipeline(qlForm)

        assert(
          result === Left(
            List(
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q5 -> q4 -> q5"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q4 -> q5 -> q4"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q3 -> q2 -> q3"),
              TypeCheckError("TypeCheckError", "Found cyclic dependency: q2 -> q3 -> q2")
            )
          )
        )
      }
    }

    "checkReferences" can {
      "and return an error when a reference to a non defined question is encountered" in {
        val qlForm = QLForm(
          "check references",
          Seq(
            Question("q1", "questions with undefined reference", IntegerType, Some(Reference("1"))),
            Question("q2", "question2", IntegerType, Some(IntegerAnswer(1))),
            Question("q3", "question3", IntegerType, Some(Reference("q2")))
          )
        )
        val result = TypeCheckFacade.pipeline(qlForm)

        assert(result === Left(List(TypeCheckError("TypeCheckError", "Undefined reference: 1"))))
      }

      "and return an error when a reference to a non defined question is encountered somewhere in an expression" in {
        val qlForm = QLForm(
          "check references in expressions",
          Seq(
            Question(
              "q1",
              "questions with undefined reference",
              IntegerType,
              Some(Minus(Reference("1")))
            ),
            Question("q2", "question2", IntegerType, Some(IntegerAnswer(1))),
            Question("q3", "question3", IntegerType, Some(Reference("q2")))
          )
        )
        val result = TypeCheckFacade.pipeline(qlForm)

        assert(result === Left(List(TypeCheckError("TypeCheckError", "Undefined reference: 1"))))
      }

      "and return an error when a reference to a non defined question is encountered within an if statement" in {
        val qlForm = QLForm(
          "check references",
          Seq(
            Conditional(Reference("a"), List.empty)
          )
        )

        val result = TypeCheckFacade.pipeline(qlForm)
        assert(result === Left(List(TypeCheckError("TypeCheckError", "Undefined reference: a"))))
      }
    }
  }
}

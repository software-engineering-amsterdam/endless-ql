package nl.uva.se.sc.niro.typechecking

import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.errors.Warning
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import nl.uva.se.sc.niro.model.ql.expressions.{ BinaryOperation, Reference, UnaryOperation }
import org.scalatest.WordSpec

class TypeCheckerTest extends WordSpec {

  "TypeCheckerTest" should {

    "checkDuplicateLabels" in {
      val qlForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "duplicate-label", IntegerType, IntegerAnswer(1)),
          Question("q2", "duplicate-label", IntegerType, IntegerAnswer(1))
        ))

      val result = TypeChecker.pipeline(qlForm)

      assert(
        result === Right(
          QLForm(
            "duplicateLabel",
            List(
              Question("q1", "duplicate-label", IntegerType, IntegerAnswer(Some(1))),
              Question("q2", "duplicate-label", IntegerType, IntegerAnswer(Some(1)))
            ),
            List(Warning("Warning: questions q1, q2 have duplicate label: duplicate-label"))
          )
        ))
    }

    "checkOperandsOfInvalidTypeToOperators" can {
      "return error for operands of invalid type to operator" in {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, BinaryOperation(Mul, StringAnswer(), StringAnswer()))
          ))

        val result = TypeChecker.pipeline(qlForm)

        assert(result === Left(List(TypeCheckError("TypeCheckError", "Operand: StringType of invalid type to operator: Mul"))))
      }

      "return error for operands of invalid type to eachother" in {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, BinaryOperation(Eq, StringAnswer(), IntegerAnswer()))
          ))

        val result = TypeChecker.pipeline(qlForm)

        assert(result === Left(List(TypeCheckError("TypeCheckError","Operands of invalid type: StringType, IntegerType"))))
      }

      "return no error for operands of different types but valid: Decimal and Integer" ignore {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, BinaryOperation(Eq, DecimalAnswer(), IntegerAnswer()))
          ))

        val result = TypeChecker.pipeline(qlForm)

        assert(result === Right(qlForm))
      }

      "return no error for operands of different types but valid: Money and Integer" ignore {
        val qlForm = QLForm(
          "invalidTypes",
          Seq(
            Question("q1", "duplicate-label", IntegerType, BinaryOperation(Eq, MoneyAnswer(), IntegerAnswer()))
          ))

        val result = TypeChecker.pipeline(qlForm)

        assert(result === Right(qlForm))
      }
    }

    "checkNonBooleanPredicates" in {
      val qLForm = QLForm(
        "duplicateLabel",
        Seq(
          Conditional(BinaryOperation(And, BooleanAnswer(true), BooleanAnswer(false)), Seq.empty),
          Conditional(BinaryOperation(Mul, IntegerAnswer(5), IntegerAnswer(1)), Seq.empty)
        )
      )

      val result = TypeChecker.pipeline(qLForm)
      assert(result === Left(List(TypeCheckError(
        "TypeCheckError",
        "Non boolean predicate: List(Conditional(BinaryOperation(Mul,IntegerAnswer(Some(5)),IntegerAnswer(Some(1))),List()))"))))
    }

    "checkDuplicateQuestionDeclarationsWithDifferentTypes" in {
      val qLForm = QLForm(
        "duplicateLabel",
        Seq(
          Question("q1", "duplicate identifier", IntegerType, IntegerAnswer(1)),
          Question("q1", "duplicate identifier", BooleanType, IntegerAnswer(1))
        )
      )

      val result = TypeChecker.pipeline(qLForm)
      assert(
        result === Left(List(TypeCheckError(
          "TypeCheckError",
          "Duplicate question declarations with different types: List(List(Question(q1,duplicate identifier,IntegerType,IntegerAnswer(Some(1))), Question(q1,duplicate identifier,BooleanType,IntegerAnswer(Some(1)))))"
        ))))
    }

    "checkCyclicDependenciesBetweenQuestions" can {
      "return the AST when no cyclic references are found" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, Reference("q2")),
            Question("q2", "question2", IntegerType, Reference("q3")),
            Question("q3", "question3", IntegerType, IntegerAnswer())
          )
        )
        val result = TypeChecker.pipeline(qlForm)

        assert(Right(qlForm) === result)
      }

      "and return an error when cyclic references are found" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, Reference("q3")),
            Question("q2", "question2", IntegerType, Reference("q1")),
            Question("q3", "question3", IntegerType, Reference("q2"))
          )
        )
        val result = TypeChecker.pipeline(qlForm)
        assert(
          result === Left(
            List(TypeCheckError(
              "TypeCheckError",
              "Found cyclic dependencies: List(q1 -> q3 -> q2 -> q1, q2 -> q1 -> q3 -> q2, q3 -> q2 -> q1 -> q3)"))))
      }

      "and return an error when cyclic references are found inside expressions" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, Reference("q3")),
            Question("q2", "question2", IntegerType, Reference("q1")),
            Question("q3", "question3", IntegerType, UnaryOperation(Sub, Reference("q2")))
          )
        )

        val result = TypeChecker.pipeline(qlForm)
        assert(
          result === Left(
            List(TypeCheckError(
              "TypeCheckError",
              "Found cyclic dependencies: List(q1 -> q3 -> q2 -> q1, q2 -> q1 -> q3 -> q2, q3 -> q2 -> q1 -> q3)"))))
      }

      "and return an error when cyclic references are found inside expressions with multiple paths" in {
        val qlForm = QLForm(
          "duplicateLabel",
          Seq(
            Question("q1", "question1", IntegerType, BinaryOperation(Mul, Reference("q2"), Reference("q3"))),
            Question("q2", "question2", IntegerType, Reference("q3")),
            Question("q2", "question2", IntegerType, Reference("q1")),
            Question("q3", "question3", IntegerType, UnaryOperation(Sub, IntegerAnswer()))
          )
        )

        val result = TypeChecker.pipeline(qlForm)
        assert(
          result === Left(
            List(TypeCheckError("TypeCheckError", "Found cyclic dependencies: List(q1 -> q2 -> q1, q2 -> q1 -> q2)"))))
      }

      "and return an error when multiple cyclic references are found" in {
        val qlForm = QLForm(
          "cyclicDependencies",
          Seq(
            Question("q1", "question1", IntegerType, IntegerAnswer()),
            Question("q2", "question2", IntegerType, Reference("q3")),
            Question("q3", "question3", IntegerType, Reference("q2")),
            Question("q4", "question4", IntegerType, Reference("q5")),
            Question("q5", "question5", IntegerType, Reference("q4"))
          )
        )

        val result = TypeChecker.pipeline(qlForm)
        assert(
          result === Left(
            List(TypeCheckError(
              "TypeCheckError",
              "Found cyclic dependencies: List(q2 -> q3 -> q2, q3 -> q2 -> q3, q4 -> q5 -> q4, q5 -> q4 -> q5)"))))
      }
    }

    "checkReferences" can {
      "and return an error when a reference to a non defined question is encountered" in {
        val qlForm = QLForm(
          "check references",
          Seq(
            Question("q1", "questions with undefined reference", IntegerType, Reference("1")),
            Question("q2", "question2", IntegerType, IntegerAnswer(1)),
            Question("q3", "question3", IntegerType, Reference("q2"))
          )
        )
        val result = TypeChecker.pipeline(qlForm)
        assert(result === Left(List(TypeCheckError("TypeCheckError", "Undefined references: List(1)"))))
      }

      "and return an error when a reference to a non defined question is encountered somewhere in an expression" in {
        val qlForm = QLForm(
          "check references in expressions",
          Seq(
            Question(
              "q1",
              "questions with undefined reference",
              IntegerType,
              UnaryOperation(Sub, Reference("1"))
            ),
            Question("q2", "question2", IntegerType, IntegerAnswer(1)),
            Question("q3", "question3", IntegerType, Reference("q2"))
          )
        )
        val result = TypeChecker.pipeline(qlForm)
        assert(result === Left(List(TypeCheckError("TypeCheckError", "Undefined references: List(1)"))))
      }

      "and return an error when a reference to a non defined question is encountered within an if statement" in {
        val qlForm = QLForm(
          "check references",
          Seq(
            Conditional(Reference("a"), List.empty)
          )
        )
        val result = TypeChecker.pipeline(qlForm)
        assert(result === Left(List(TypeCheckError("TypeCheckError", "Undefined references: List(a)"))))
      }
    }
  }
}

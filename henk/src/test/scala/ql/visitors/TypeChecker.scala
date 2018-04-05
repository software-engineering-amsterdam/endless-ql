import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class TypeCheckerSpec extends FunSpec {
  val resourceDir = "ql/typechecking"

  describe("when TypeChecker validates a valid form") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))
    val tc = new TypeChecker()

    it("typechecker should not raise an exception") {
      noException should be thrownBy tc.run(form)
    }

    it("error message should be empty") {
      assert(tc.warnings.isEmpty)
    }
  }

  describe("when TypeChecker validates a form containing an undeclared var") {
    val filename = s"${resourceDir}/single_undeclared_identifier.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))
    val tc = new TypeChecker()

    it("should return false") {
      a [IdentifierNotDeclared] should be thrownBy tc.run(form)
    }
  }

  describe("when TypeChecker validates a form containing invalid conditional") {
    val filename = s"${resourceDir}/conditions/binop/money_bool_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))
    val tc = new TypeChecker()

    it("typechecker should raise an exception") {
      a [ConditionalNotBoolean] should be thrownBy tc.run(form)
    }
  }

  describe("when TypeChecker validates a form containing a duplicate question") {
    val filename = s"${resourceDir}/duplicate_question/duplicate_different_type.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))
    val tc = new TypeChecker()

    it("typechecker should raise an exception") {
      a [DuplicateQuestionDeclaration] should be thrownBy tc.run(form)
    }
  }

  describe("when TypeChecker validates a form containing a duplicate label") {
    val filename = s"${resourceDir}/duplicate_label/single_duplicate_label.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))
    val tc = new TypeChecker()

    it("no exception should have been raised") {
      noException should be thrownBy tc.run(form)
    }

    it("typechecker warnings should contain a single element") {
      tc.warnings match {
        case Some(warnings: List[String]) => {
          if(warnings.size == 1) {
            succeed
          } else {
            fail("warnings contained less or more than one element")
          }
        }
        case other => fail("no correct error was set")
      }
    }
  }

  describe("when TypeChecker validates a form containing an invalid type inference") {
    val filename = s"${resourceDir}/type_inference/invalid_simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))
    val tc = new TypeChecker()

    it("typechecker should contain the exception") {
      a [InvalidTypeInfered] should be thrownBy tc.run(form)
    }
  }
}

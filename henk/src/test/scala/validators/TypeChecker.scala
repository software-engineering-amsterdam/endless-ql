import general.validators._

import ql.spec.helpers.{ FormHelper => QLHelper }
import qls.spec.helpers.{ FormHelper => QLSHelper }

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class GeneralTypeCheckerSpec extends FunSpec {
  describe("when TypeChecker validates a valid form") {
    val ql = QLHelper.getRoot(getClass.getResource("general/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource("general/simple.qls"))
    val tc = new GeneralTypeChecker()

    it("should return true") {
      assert(tc.validate(ql, qls))
    }

    it("error message should be empty") {
      assert(tc.error == null)
    }
  }

  describe("when TypeChecker validates a form with undeclared Identifier in QLS") {
    val resourceDir = "general/validator/identifier/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
    val tc = new GeneralTypeChecker()

    it("should return false") {
      assert(!tc.validate(ql, qls))
    }

    it("error message should be a UndeclaredQuestionStyling") {
      tc.error shouldBe a [UndeclaredQuestionStyling]
    }
  }

  describe("when TypeChecker validates a form with unplaced questions") {
    val resourceDir = "general/validator/question_placement/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
    val tc = new GeneralTypeChecker()

    it("should return false") {
      assert(!tc.validate(ql, qls))
    }

    it("error message should be a UndeclaredQuestionStyling") {
      tc.error shouldBe a [UnplacedQuestion]
    }
  }

  describe("when TypeChecker validates a form with type mismatches between QL and QLS") {
    val resourceDir = "general/validator/type_checker/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/radio_int.qls"))
    val tc = new GeneralTypeChecker()

    it("should return false") {
      assert(!tc.validate(ql, qls))
    }

    it("error message should be a UndeclaredQuestionStyling") {
      tc.error shouldBe a [IncompatibleTypes]
    }
  }
}

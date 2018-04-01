import qls.validators._
import qls.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.Matchers._

class QuestionPlacementValidatorSpec extends FunSpec {
  val validator = QuestionPlacementValidator

  describe("validating a valid QLS sheet") {
    val resourceDir = "qls/validators/question_placement/valid"

    it("should yield none check is run") {
      val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
      assert(validator.check(qls).isEmpty)
    }
  }

  describe("parsing invalid") {
    val resourceDir = "qls/validators/question_placement/invalid"

    describe("simple") {
      it("should yield an exception when check is run") {
        val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
        validator.check(qls).get shouldBe a [DuplicateQuestionPlacement]
      }
    }

    describe("duplicated question nested further") {
      it("should yield an exception when check is run") {
        val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/nested.qls"))
        validator.check(qls).get shouldBe a [DuplicateQuestionPlacement]
      }
    }
  }
}

import qls.validators._
import qls.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class QuestionPlacementValidatorSpec extends FunSpec {
  val validator = new DuplicateQuestionPlacementValidator()

  describe("validating a valid QLS sheet") {
    val resourceDir = "qls/validators/question_placement/valid"

    it("should yield none check is run") {
      val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
      noException should be thrownBy validator.execute(qls)
    }
  }

  describe("parsing invalid") {
    val resourceDir = "qls/validators/question_placement/invalid"

    describe("simple") {
      it("should yield an exception when check is run") {
        val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
        a [DuplicateQuestionPlacement] should be thrownBy validator.execute(qls)
      }
    }

    describe("duplicated question nested further") {
      it("should yield an exception when check is run") {
        val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/nested.qls"))
        a [DuplicateQuestionPlacement] should be thrownBy validator.execute(qls)
      }
    }
  }
}

import general.validators._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import ql.spec.helpers.{ FormHelper => QLHelper }
import qls.spec.helpers.{ FormHelper => QLSHelper }

class GeneralQuestionPlacementValidatorSpec extends FunSpec {
  val validator = new GeneralQuestionPlacementValidator()

  describe("when checking a valid QL and QLS form") {
    val resourceDir = "general"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return None") {
      noException should be thrownBy validator.execute(ql, qls)
    }
  }

  describe("checking QL questions that appear deeply nested in a QLS form") {
    val resourceDir = "general/validator/question_placement/valid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/deeply_nested.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/deeply_nested.qls"))

    it("should return None") {
      noException should be thrownBy validator.execute(ql, qls)
    }
  }

  describe("checking for a situation in which a QL question is not placed in QLS") {
    val resourceDir = "general/validator/question_placement/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return option with exception") {
      a [UnplacedQuestion] should be thrownBy validator.execute(ql, qls)
    }

    it("should contain question name in message") {
      val caught = the [UnplacedQuestion] thrownBy(validator.execute(ql, qls))
      assert(caught.label.contains("hasSoldSecondHouse"))
    }
  }
}

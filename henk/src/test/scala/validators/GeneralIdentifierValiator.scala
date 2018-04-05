import general.validators._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import ql.spec.helpers.{ FormHelper => QLHelper }
import qls.spec.helpers.{ FormHelper => QLSHelper }

class GeneralIdentifierValidatorSpec extends FunSpec {
  val validator = new GeneralIdentifierValidator()

  describe("when checking a valid QL and QLS form") {
    val resourceDir = "general"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return None") {
      noException should be thrownBy validator.execute(ql, qls)
    }
  }

  describe("when checking an invalid QL and QLS form") {
    val resourceDir = "general/validator/identifier/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return option with exception") {
      a [UndeclaredQuestionStyling] should be thrownBy validator.execute(ql, qls)
    }
  }
}

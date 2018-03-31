import general.validators._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import ql.spec.helpers.{ FormHelper => QLHelper }
import qls.spec.helpers.{ FormHelper => QLSHelper }

class GeneralIdentifierValidatorSpec extends FunSpec {
  val validator = GeneralIdentifierValidator

  describe("when checking a valid QL and QLS form") {
    val resourceDir = "general"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return None") {
      val result = validator.check(ql, qls)
      assert(result == None)
    }
  }

  describe("when checking an invalid QL and QLS form") {
    val resourceDir = "general/validator/identifier/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return option with exception") {
      validator.check(ql, qls).get match {
        case ex: UndeclaredQuestionStyling => succeed
        case other => fail("should have thrown error")
      }
    }
  }
}

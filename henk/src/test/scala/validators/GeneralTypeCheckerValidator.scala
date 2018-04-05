import general.validators._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import ql.spec.helpers.{ FormHelper => QLHelper }
import qls.spec.helpers.{ FormHelper => QLSHelper }

class GeneralTypeCheckerValidatorSpec extends FunSpec {
  val validator = new GeneralTypeCheckerValidator()

  describe("when checking a valid QL and QLS form") {
    val resourceDir = "general/validator/type_checker"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return None") {
      assert(validator.check(ql, qls).isEmpty)
    }
  }

  describe("checking a QL with QLS form where type of radio widget is infered") {
    val resourceDir = "general/validator/type_checker/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/radio_int.qls"))
    val result = validator.check(ql, qls).get

    it("should return None") {
      result shouldBe a [IncompatibleTypes]
    }

    it("should contain question name in message") {
      result.label should include("QL has 'BooleanType'")
      result.label should include("QLS has 'IntegerType'")
    }
  }
}

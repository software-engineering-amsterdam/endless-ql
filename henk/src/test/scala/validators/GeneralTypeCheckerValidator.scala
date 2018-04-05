import general.validators._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

import ql.spec.helpers.{ FormHelper => QLHelper }
import qls.spec.helpers.{ FormHelper => QLSHelper }

class GeneralTypeCheckerValidatorSpec extends FunSpec {
  val validator = new GeneralTypeChecker()

  describe("when checking a valid QL and QLS form") {
    val resourceDir = "general/validator/type_checker"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should return None") {
      noException should be thrownBy validator.run(ql, qls)
    }
  }

  describe("checking a QL with QLS form where type of radio widget is infered") {
    val resourceDir = "general/validator/type_checker/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/radio_int.qls"))

    it("should throw an exception") {
      a [IncompatibleTypes] should be thrownBy validator.run(ql, qls)
    }

    it("label in exception should contain information regarding the situation") {
      val caught = the [IncompatibleTypes] thrownBy(validator.run(ql, qls))
      assert(caught.label.contains("QL has 'BooleanType'"))
      assert(caught.label.contains("QLS has 'IntegerType'"))
    }
  }

  describe("checking a QL with QLS form where QLS is styling an undeclared question") {
    val resourceDir = "general/validator/identifier/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should throw an exception") {
      a [UndeclaredQuestionStyling] should be thrownBy validator.run(ql, qls)
    }

    it("label in exception should contain information regarding the situation") {
      val caught = the [UndeclaredQuestionStyling] thrownBy(validator.run(ql, qls))
      assert(caught.label.contains("appears in QLS but not in QL"))
    }
  }

  describe("checking a QL with QLS form where not all questions are placed in QLS") {
    val resourceDir = "general/validator/question_placement/invalid"
    val ql = QLHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.ql"))
    val qls = QLSHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))

    it("should throw an exception") {
      a [UnplacedQuestion] should be thrownBy validator.run(ql, qls)
    }

    it("label in exception should contain information regarding the situation") {
      val caught = the [UnplacedQuestion] thrownBy(validator.run(ql, qls))
      assert(caught.label.contains("is declared in QL but not placed in QLS"))
    }
  }
}

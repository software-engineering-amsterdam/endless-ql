import qls.validators._
import qls.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import org.scalatest.Matchers._

class WidgetTypeCheckerValidatorSpec extends FunSpec {
  val validator = new WidgetTypeCheckerValidator()

  describe("validating a valid QLS sheet") {
    val resourceDir = "qls/validators/widget_type_checker/valid"

    it("should yield none check is run") {
      val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
      noException should be thrownBy validator.execute(qls)
    }

    it("configuration missing widget styling declaration should still pass") {
      val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/missing_widget_configuration.qls"))
      noException should be thrownBy validator.execute(qls)
    }

    it("default integer with text widget") {
      val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/text_int.qls"))
      noException should be thrownBy validator.execute(qls)
    }

    it("default string with text widget") {
      val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/text_string.qls"))
      noException should be thrownBy validator.execute(qls)
    }
  }

  describe("parsing invalid") {
    val resourceDir = "qls/validators/widget_type_checker/invalid"

    describe("simple") {
      it("should yield an exception when check is run") {
        val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/simple.qls"))
        a [IncompatibleWidgetType] should be thrownBy validator.execute(qls)
      }
    }

    describe("text widget") {
      it("should yield exception since boolean fields and text widget aren't compatible") {
        val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/text_boolean.qls"))
        a [IncompatibleWidgetType] should be thrownBy validator.execute(qls)
      }
    }

    // describe("duplicated question nested further") {
      // it("should yield an exception when check is run") {
        // val qls = FormHelper.getRoot(getClass.getResource(s"${resourceDir}/nested.qls"))
        // validator.check(qls).get shouldBe a [DuplicateQuestionPlacement]
      // }
    // }
  }
}

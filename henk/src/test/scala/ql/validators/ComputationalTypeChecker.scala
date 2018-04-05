import ql.models.ast._
import ql.validators._
import ql.spec.helpers._

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class TypeInferenceValdatorSpec extends FunSpec {
  val resourceDir = "ql/typechecking/type_inference"
  val validator = new TypeInferenceValidator()

  describe("when form contains only type valid assignments") {
    val filename = s"${resourceDir}/simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should not raise an exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe("when form contains an type invalid assignment") {
    val filename = s"${resourceDir}/invalid_simple.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should raise an exception") {
      an [InvalidTypeInfered] should be thrownBy validator.execute(form)
    }
  }

  describe("when form contains type valid boolean binop assignment") {
    val filename = s"${resourceDir}/boolean_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should not raise an exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe("when form contains an type invalid boolean binop assignment") {
    val filename = s"${resourceDir}/invalid_string_boolean_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should raise an exception") {
      an [InvalidTypeInfered] should be thrownBy validator.execute(form)
    }
  }

  describe("when form contains type valid identifier boolean binop assignment") {
    val filename = s"${resourceDir}/valid_identifier_boolean_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should not raise an exception") {
      noException should be thrownBy validator.execute(form)
    }
  }

  describe(
    "when form contains an type invalid indentifier boolean binop assignment") {
    val filename = s"${resourceDir}/invalid_identifier_boolean_binop.ql"
    val form = FormHelper.getRoot(getClass.getResource(filename))

    it("execute should raise an exception") {
      an [InvalidTypeInfered] should be thrownBy validator.execute(form)
    }
  }
}
